package jmx;

public interface ObserverMBean {

    public long getMissedHits();

    public long getTotalHits();

    public double  getPercentage();

}
