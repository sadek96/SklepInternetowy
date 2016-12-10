/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Klient;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.HibernateUtil;

/**
 *
 * @author Lukasz
 */
public class KlientDao {

    public static boolean checkLogin(String login, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            String queryString = "from Klient where login = :login and haslo= :password";
            Query query = session.createQuery(queryString);
            query.setString("login", login);
            query.setString("password", password);
            if (query.uniqueResult() != null) {

                return true;
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        session.close();
        return false;
    }

    public static Klient getKlient(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Klient k;
        try {
            Criteria criteria = session.createCriteria(Klient.class);
            k = (Klient) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            return k;
        } catch (Exception e) {
            return null;
        } finally {
            session.flush();
            session.close();
        }

    }

    public static void addKlient(Klient k) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(k);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
        } finally {
            session.close();
        }

    }
    
    

    public static boolean isExist(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from Klient where login = :login");
            query.setString("login", login);
            if (query.uniqueResult() == null) {
                return true; //jeśli nie istnieje
            }
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return false;
    }
}
