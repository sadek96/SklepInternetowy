/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Adres;
import model.Klient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.HibernateUtil;

/**
 *
 * @author Lukasz
 */
public class AdresDao {

    public static Adres getAdres(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Adres a;
        try {
            Criteria criteria = session.createCriteria(Adres.class);
            a = (Adres) criteria.add(Restrictions.eq("Klient_id", id)).uniqueResult();
            return a;
        } catch (Exception e) {
            return null;
        } finally {
            session.flush();
            session.close();
        }

    }

    public static void addAdres(Adres a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(a);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
        } finally {
            session.close();
        }

    }

}
