/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Kategoria;
import model.Produkt;
import model.Zdjecie;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class ProduktDao {

    public List<Produkt> getAll() {
        List<Produkt> produkty = new ArrayList<Produkt>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            produkty = session.createQuery("from Produkt").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return produkty;
    }

    public Produkt getById(int id) {
        Produkt produkt = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            produkt = (Produkt) session.get(Produkt.class, id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return produkt;
    }

    public List<Produkt> getPagedProducts(String kategoria, int strona, int ilośćWyświetlana) {
        List<Produkt> produkty = new ArrayList<Produkt>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = null;
            if (kategoria.equals("Wszystkie")) {
                query = session.createQuery("from Produkt");
            } else {
                query = session.createQuery("select produkt from Produkt as produkt where produkt.kategoriaProducent.kategoria.nazwaKategorii=:nazwa");
                query.setString("nazwa", kategoria);
            }

            query.setFirstResult((strona - 1) * ilośćWyświetlana);
            query.setMaxResults(ilośćWyświetlana);
            produkty = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return produkty;
    }

    public int getPageCount(int ilośćWyświetlana) {
        int pageCount = 1;

        pageCount = (getAll().size() + ilośćWyświetlana - 1) / ilośćWyświetlana;

        return pageCount;
    }

    public List<Produkt> getProductsWithCategory(String nazwa) {

        List<Produkt> produkty = new ArrayList<Produkt>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query;
            if(nazwa.equals("Wszystkie")){
                query=session.createQuery("from Produkt");
            }
                else
            { query = session.createQuery("select produkt from Produkt as produkt where produkt.kategoriaProducent.kategoria.nazwaKategorii=:nazwa");
            query.setString("nazwa", nazwa);}
            produkty = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return produkty;
    }

    public List<Zdjecie> getZdjecia(int idProduktu) {
        List<Zdjecie> zdjecia = new ArrayList<Zdjecie>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("select zdjecie from Zdjecie as zdjecie where zdjecie.produkt.idProdukt=:idProduktu ");
            query.setInteger("idProduktu", idProduktu);
            zdjecia = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return zdjecia;
    }

    public void addProdukt(Produkt p){
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
}
