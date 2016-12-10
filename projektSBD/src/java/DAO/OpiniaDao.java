/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Opinia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class OpiniaDao {

    public List<Opinia> getAllByProduktId(int id) {
        List<Opinia> opinie = new ArrayList<Opinia>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from Opinia as opinia where opinia.produkt.idProdukt = :produktId ");
            query.setString("produktId", String.valueOf(id));
            opinie = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return opinie;
    }

    public void add(Opinia opinia){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trns = null;
        trns = session.beginTransaction();
        try {
            session.save(opinia);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
}
