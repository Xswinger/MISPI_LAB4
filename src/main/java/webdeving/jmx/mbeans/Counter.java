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

@ManagedBean(name = "counter")
@ApplicationScoped
public class Counter extends StandardMBean implements CounterMBean {

    private static final Logger logger = Logger.getLogger(Counter.class.getName());
    private final String name = "counter";
    private final String nameOfObject = "BeanRegister:name=" + name;
    @ManagedProperty("#{beanRegistrator}")
    private BeanRegistrator beanRegistrator;
    private final AtomicLong totalHits = new AtomicLong(0);
    private final AtomicLong missedHits = new AtomicLong(0);

    public Counter() throws NotCompliantMBeanException {
        super(CounterMBean.class);
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
        long currentTotal = totalHits.incrementAndGet();
        if (!isSuccess) {
            missedHits.incrementAndGet();
        }
        if (currentTotal % 5 == 0) {
            logger.info("============ Кратно пяти ============");
        }
    }

    public void clear() {
        totalHits.set(0);
        missedHits.set(0);
    }

    @Override
    public long getMissedHits() {
        return missedHits.get();
    }

    @Override
    public long getTotalHits() {
        return totalHits.get();
    }

    public BeanRegistrator getBeanRegistrator() {
        return beanRegistrator;
    }

    public void setBeanRegistrator(BeanRegistrator beanRegistrator) {
        this.beanRegistrator = beanRegistrator;
    }
}
