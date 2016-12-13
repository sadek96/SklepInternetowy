/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class ZamowienieDao {

    public void editStatus(String status, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("update Zamowienie set Stan_zamowienia = :status" + " where Nr_zamowienia = :id");
            query.setParameter("status", status);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
        } finally {
            session.close();
        }
    }
}
