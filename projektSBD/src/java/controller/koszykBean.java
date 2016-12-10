/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProduktDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ZamowienieProdukt;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class koszykBean implements Serializable {

    List<ZamowienieProdukt> produkty;

    ProduktDao produktDao;

    int produktCount;
    float totalValue;

    /**
     * Creates a new instance of koszykBean
     */
    public koszykBean() {
        produkty = new ArrayList<ZamowienieProdukt>();
        produktDao = new ProduktDao();
    }

    public String addProdukt(int id) {
        for (ZamowienieProdukt produkt : produkty) {
            if (produkt.getProdukt().getIdProdukt() == id) {
                incrementProduktCount(id);
                return null;
            }
        }
        ZamowienieProdukt p = new ZamowienieProdukt();
        p.setProdukt(produktDao.getById(id));
        p.setIloscProduktu(1);
        produkty.add(p);
        return null;
    }

    public String removeProdukt(int id) {
        for (ZamowienieProdukt produkt : produkty) {
            if (produkt.getProdukt().getIdProdukt() == id) {
                produkty.remove(produkty.indexOf(produkt));
            }
            return null;
        }
        return null;
    }

    public int getProduktCount() {
        produktCount = 0;
        if (!produkty.isEmpty()) {
            for (ZamowienieProdukt produkt : produkty) {
                produktCount += produkt.getIloscProduktu();
            }
        }
        return produktCount;
    }

    public String incrementProduktCount(int id) {
        for (ZamowienieProdukt produkt : produkty) {
            if (produkt.getProdukt().getIdProdukt() == id) {
                produkt.setIloscProduktu(produkt.getIloscProduktu() + 1);
            }
            return null;
        }
        return null;
    }

    public String decrementProduktCount(int id) {
        for (ZamowienieProdukt produkt : produkty) {
            if (produkt.getProdukt().getIdProdukt() == id) {
                if (produkt.getIloscProduktu() > 1) {
                    produkt.setIloscProduktu(produkt.getIloscProduktu() - 1);
                }
                return null;
            }
        }
        return null;
    }

    public void setProduktCount(int produktCount) {
        this.produktCount = produktCount;
    }

    public float getTotalValue() {
        totalValue = 0;
        if (!produkty.isEmpty()) {
            for (ZamowienieProdukt produkt : produkty) {
                totalValue += produkt.getIloscProduktu()*produkt.getProdukt().getCenaBruttoAktualna();
            }
        }
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public List<ZamowienieProdukt> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<ZamowienieProdukt> produkty) {
        this.produkty = produkty;
    }

}
