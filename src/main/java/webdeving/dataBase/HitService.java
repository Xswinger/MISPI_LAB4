package webdeving.dataBase;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import webdeving.entity.Hit;

import java.io.Serializable;
import java.util.List;

public class HitService implements Serializable, HitDao {

    private final SessionFactory manager = ConnectionManager.getSessionFactory();

    @Override
    public void add(Hit hit) {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.persist(hit);
        currentSession.getTransaction().commit();
    }

    @Override
    public List<Hit> getAll() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        return currentSession.createQuery( "FROM Hit ").list();
    }

    @Override
    public void clear() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.clear();
        currentSession.getTransaction().commit();
    }
}
