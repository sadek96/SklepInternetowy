/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;

import model.Kategoria;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class KategoriaDao {

    public void add(Kategoria kategoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trns = null;
        trns = session.beginTransaction();
        try {
            session.save(kategoria);
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

    public void delete(int kategoriaId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();
            Kategoria kat = (Kategoria) session.load(Kategoria.class, new Integer(kategoriaId));
            session.delete(kat);
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

    public List<Kategoria> getAll() {
        List<Kategoria> kategorie = new ArrayList<Kategoria>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kategorie = session.createQuery("from Kategoria").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return kategorie;
    }

    public Kategoria getById(int id) {
        Kategoria kategoria = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            kategoria = (Kategoria) session.get(Kategoria.class, id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return kategoria;
    }

    public Kategoria getByName(String name) {
        
       // Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Kategoria kategoria ;
        try {
           // Criteria criteria = session.createCriteria(Kategoria.class);
           // kategoria = (Kategoria) criteria.add(Restrictions.eq("nazwa_kategorii", name)).uniqueResult();
           Query query=session.createQuery("from Kategoria where Nazwa_kategorii = :name");
           query.setString("name", name);
           kategoria = (Kategoria) query.uniqueResult();
            return kategoria;
        } catch (Exception e) {
            
            return null;
        } finally {
            session.flush();
            session.close();

        }

    }
}
