/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import model.Kategoria;
import org.hibernate.exception.ConstraintViolationException;

import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class KategoriaDao {

    private Kategoria kategoria;
    private HibernateUtil helper;
    private Session session;
    private String nazwa;

    public String getNazwa() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            kategoria = (Kategoria) session.get(Kategoria.class, 2);
            this.nazwa = kategoria.getNazwaKategorii();
        } catch (Exception e) {
            this.nazwa = "NULL";
        } finally {
            return nazwa;
        }

    }

    public void dodajKategoria() {
        kategoria = new Kategoria();
        kategoria.setNazwaKategorii("Komputer osobisty");
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(kategoria);
            session.getTransaction().commit();
        } catch (ConstraintViolationException cve) {

        }
        session.close();
    }

}
