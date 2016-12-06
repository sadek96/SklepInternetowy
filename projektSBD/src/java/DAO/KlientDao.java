/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
}
