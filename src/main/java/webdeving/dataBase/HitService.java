package webdeving.dataBase;

import jakarta.persistence.criteria.CriteriaBuilder;
import jmx.Observer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import webdeving.entity.Hit;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.List;

public class HitService implements Serializable, HitDao {

    private final SessionFactory manager = ConnectionManager.getSessionFactory();
    //  MBean injection start
    private Observer observer;

    public HitService() {
        try {

            //  Get the platform MBeanServer
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            //  Unique identification of MBeans
            observer = new Observer();
            ObjectName beanName = null;

            //  Uniquely identify the MBeans and register them with the platform MBeanServer
            beanName = new ObjectName("BeanRegister:name=observe");
            mbs.registerMBean(observer, beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //  MBean injection end

    @Override
    public void add(Hit hit) {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.persist(hit);
        currentSession.getTransaction().commit();
        //  MBean injection start
        observer.increment(hit.isSuccess());
        //  MBean injection end
    }

    @Override
    public List<Hit> getAll() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        List<Hit> hits =  currentSession.createQuery( "FROM Hit ").list();
        currentSession.getTransaction().commit();
        //  MBean injection start
        for (Hit hit : hits) {
            observer.increment(hit.isSuccess());
        }
        //  MBean injection end
        return hits;
    }

    @Override
    public void clear() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.clear();
        currentSession.getTransaction().commit();
        //  MBean injection start
        observer.clear();
        //  MBean injection end
    }
}
