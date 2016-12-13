/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import model.Producent;
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
public class ProducentDao {

    public ArrayList<Producent> getAll() {
        ArrayList<Producent> list = new ArrayList<Producent>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from Producent");
            list = (ArrayList<Producent>) query.list();
        } catch (Exception e) {
            //return null;
        } finally {
            session.close();
            return list;

        }
    }

    public Producent findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
         //   Criteria criteria=session.createCriteria(Producent.class);
           // return (Producent) criteria.add(Restrictions.eq("Nazwa_producenta", name)).uniqueResult();
           Query query=session.createQuery("from Producent where Nazwa_producenta = :name");
           query.setString("name", name);
           return (Producent) query.uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void addProducent(Producent p){
    Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
        } finally {
            session.close();
        }}
}
