package webdeving.jmx.mbeans;

import webdeving.jmx.util.BeanRegistrator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.management.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@ManagedBean(name = "percenter")
@ApplicationScoped
public class Percenter extends StandardMBean implements PercenterMBean {

    private final String name = "percenter";
    private final String nameOfObject = "BeanRegister:name=" + name;
    @ManagedProperty("#{beanRegistrator}")
    private BeanRegistrator beanRegistrator;
    private final AtomicLong totalHits = new AtomicLong(0);
    private final AtomicLong missedHits = new AtomicLong(0);
    private String percentage;

    public Percenter() throws NotCompliantMBeanException {
        super(PercenterMBean.class);
    }

    @PostConstruct
    private void init() {
        beanRegistrator.register(nameOfObject, this);
    }

    @PreDestroy
    private void destroy() {
        beanRegistrator.unregister(nameOfObject);
    }

    public void increment(boolean isSuccess) {
        totalHits.incrementAndGet();
        if (!isSuccess) {
            missedHits.incrementAndGet();
        }
        updatePercentage();
    }

    public void clear() {
        totalHits.set(0);
        missedHits.set(0);
        updatePercentage();
    }

    private void updatePercentage() {
        percentage = (totalHits.get() != 0 ? (double) missedHits.get() / totalHits.get() * 100 : 0) + "%";
    }

    @Override
    public String getPercentage() {
        return percentage;
    }

    public BeanRegistrator getBeanRegistrator() {
        return beanRegistrator;
    }

    public void setBeanRegistrator(BeanRegistrator beanRegistrator) {
        this.beanRegistrator = beanRegistrator;
    }
}
