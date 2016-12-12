/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.util.ArrayList;
import java.util.List;
import model.PozycjaFaktury;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Daniel
 */
public class PozycjaFakturyDao {
    
    
    public void save(PozycjaFaktury pozycja){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trns = null;
        trns = session.beginTransaction();
        try {
            session.save(pozycja);
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
    
    public List<PozycjaFaktury> getAllByFakturaId(int id){
        List<PozycjaFaktury> pozf = new ArrayList<PozycjaFaktury>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from PozycjaFaktury as pf left join fetch pf.produkt as pr where pf.faktura.idFaktura = :id");
            query.setString("id", String.valueOf(id));
           pozf = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return pozf;
    }
}
