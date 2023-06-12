package webdeving.dataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import webdeving.entity.Hit;
import webdeving.jmx.util.Notifier;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "hitService")
@ApplicationScoped
public class HitService implements Serializable, HitDao {

    private final SessionFactory manager = ConnectionManager.getSessionFactory();

    @ManagedProperty("#{notifier}")
    private Notifier notifier;

    public HitService() {
    }

    @Override
    public void add(Hit hit) {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.persist(hit);
        currentSession.getTransaction().commit();
        notifier.notifyAdding(hit.isSuccess());
    }

    @Override
    public List<Hit> getAll() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        List<Hit> hits = currentSession.createQuery("FROM Hit ").list();
        currentSession.getTransaction().commit();
        for (Hit hit : hits) {
            notifier.notifyAdding(hit.isSuccess());
        }
        return hits;
    }

    @Override
    public void clear() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.clear();
        currentSession.getTransaction().commit();
        notifier.notifyClearing();
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

}
