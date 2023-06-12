package webdeving.jmx.util;

import webdeving.jmx.mbeans.Counter;
import webdeving.jmx.mbeans.Percenter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "notifier")
@ApplicationScoped
public class Notifier {

    @ManagedProperty("#{counter}")
    Counter counter;
    @ManagedProperty("#{percenter}")
    Percenter percenter;

    public Notifier() {
    }

    public void notifyAdding(boolean isSuccess) {
        counter.increment(isSuccess);
        percenter.increment(isSuccess);
    }

    public void notifyClearing() {
        counter.clear();
        percenter.clear();
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Percenter getPercenter() {
        return percenter;
    }

    public void setPercenter(Percenter percenter) {
        this.percenter = percenter;
    }
}
