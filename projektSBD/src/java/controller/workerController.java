/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KategoriaDao;
import DAO.ProduktDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Kategoria;
import model.KategoriaProducent;
import model.Produkt;

/**
 *
 * @author Lukasz
 */
@ManagedBean
@SessionScoped
public class workerController {
    
    private KategoriaProducent k;
    private Produkt p;
    private ProduktDao produktDao = new ProduktDao();
    private KategoriaDao kategoriaDao = new KategoriaDao();
    
    public void add() {
        p.setKategoriaProducent(k);
        produktDao.addProdukt(p);
    }
    
    public Produkt getP() {
        return p;
    }
    
    public void setP(Produkt p) {
        this.p = p;
    }
    
    public KategoriaProducent getK() {
        return k;
    }
    
    public void setK(KategoriaProducent k) {
        this.k = k;
    }
    
}
