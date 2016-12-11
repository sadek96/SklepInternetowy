/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Pracownik;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author Lukasz
 */
public class PracownikDao {
    
    public boolean checkLogin(String login, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            String queryString = "from Pracownik where login = :login and haslo= :password";
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

    public  Pracownik getWorker(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Pracownik k;
        try {
            Criteria criteria = session.createCriteria(Pracownik.class);
            k = (Pracownik) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            return k;
        } catch (Exception e) {
            return null;
        } finally {
            session.flush();
            session.close();
        }

    }
    
    
}
