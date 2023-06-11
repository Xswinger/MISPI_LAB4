package jmx;

import javax.faces.component.FacesComponent;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


public class Observer extends StandardMBean implements ObserverMBean {

    private static final Logger logger = Logger.getLogger(Observer.class.getName());
    private final AtomicLong totalHits = new AtomicLong(0);
    private final AtomicLong missedHits = new AtomicLong(0);
    private double percentage = 0d;

    public Observer() throws NotCompliantMBeanException {
        super(ObserverMBean.class);
    }

    public void increment(boolean isSuccess) {
        long currentTotal = totalHits.incrementAndGet();
        if (!isSuccess) {
            missedHits.incrementAndGet();
        }
        if (currentTotal % 5 == 0) {
            logger.info("============ Кратно пяти ============");
        }
        refreshPercentage();
    }

    public void clear() {
        totalHits.set(0);
        missedHits.set(0);
        refreshPercentage();
    }

    private void refreshPercentage() {
        percentage = ((double) missedHits.get() / totalHits.get()) * 100;
    }

    @Override
    public long getMissedHits() {
        return missedHits.get();
    }

    @Override
    public long getTotalHits() {
        return totalHits.get();
    }

    @Override
    public double getPercentage() {
        return percentage;
    }
}
