/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.KategoriaProducent;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class KategoriaProducentDao {

    public void addKategoriaProducent(KategoriaProducent p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
        } finally {
            session.close();
        }
    }

    public KategoriaProducent getKP(int id, int id2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
          Query query=session.createQuery("from KategoriaProducent where (Producent_id = :id and Kategoria_id = :id2");
          query.setParameter("id", id);
          query.setParameter("id2", id2);
          return (KategoriaProducent) query.uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }
}
