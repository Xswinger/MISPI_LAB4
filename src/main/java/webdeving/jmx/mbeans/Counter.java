package webdeving.jmx.mbeans;

import webdeving.jmx.util.BeanRegistrator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.management.*;
import java.util.concurrent.atomic.AtomicLong;

@ManagedBean(name = "counter")
@ApplicationScoped
public class Counter extends NotificationBroadcasterSupport implements CounterMBean {

    private final String name = "counter";
    private final String nameOfObject = "BeanRegister:name=" + name;
    @ManagedProperty("#{beanRegistrator}")
    private BeanRegistrator beanRegistrator;
    private final int MULTIPLIER = 5;
    private long sequenceNumber = 0;
    private final AtomicLong totalHits = new AtomicLong(0);
    private final AtomicLong missedHits = new AtomicLong(0);

    public Counter() {
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
        if (currentTotal % MULTIPLIER == 0) {
            Notification notification = new Notification("Кратность", this, sequenceNumber++, "Кратно " + MULTIPLIER);
            notification.setUserData(currentTotal);
            sendNotification(notification);
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
