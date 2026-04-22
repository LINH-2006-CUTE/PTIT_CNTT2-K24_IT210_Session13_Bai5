package spring_boot.session13_it210_bai5.repo.impl;

import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PrescriptionRepositoryImpl {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Prescription> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("SELECT DISTINCT p FROM Prescription p LEFT JOIN FETCH p.details", Prescription.class).list();
        } finally {
            session.close();
        }
    }

    public void save(Prescription p) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}