package spring_boot.session13_it210_bai5.repo.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_boot.session13_it210_bai5.model.Prescription;

import java.util.List;

@Repository
public class PrescriptionRepositoryImpl {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Prescription> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT DISTINCT p FROM Prescription p LEFT JOIN FETCH p.details", Prescription.class).list();
        }
    }

    public void save(Prescription p) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}