/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KategoriaDao;
import DAO.KategoriaProducentDao;
import DAO.ProducentDao;
import DAO.ProduktDao;
import DAO.ZamowienieDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Kategoria;
import model.KategoriaProducent;
import model.Producent;
import model.Produkt;

/**
 *
 * @author Lukasz
 */
@ManagedBean
@SessionScoped
public class WorkerController {

    private String producent;
    private String kategoria, stan;
    private int idzamowienia;
    private ZamowienieDao zamowienieDao = new ZamowienieDao();
    private Kategoria category;
    private Producent producentP;
    private ProducentDao producentDao = new ProducentDao();
    private KategoriaDao kategoriaDao = new KategoriaDao();
    private ProduktDao produktDao = new ProduktDao();
    private KategoriaProducent kategoriaProducent = new KategoriaProducent();
    private KategoriaProducentDao kategoriaProducentDao = new KategoriaProducentDao();

    private String nazwa, opis;
    private float cenaB, cenaN, vat;
    private int ilosc;

    private ArrayList<String> list = new ArrayList<String>();
    private List<String> klist = new ArrayList<String>();

    public List<String> getCategory() {
        List<Kategoria> l = kategoriaDao.getAll();
        klist = new ArrayList<String>();
        for (int i = 0; i < l.size(); i++) {
            klist.add(l.get(i).getNazwaKategorii());
        }

        return klist;
    }

    public ArrayList<String> getAll() {
        List<Producent> l = producentDao.getAll();
        list = new ArrayList<String>();
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getNazwaProducenta());
        }
        return list;
    }

    public void update() {
        zamowienieDao.editStatus(stan, idzamowienia);
    }

    public void add() {
        Produkt p = new Produkt();
        p.setIlosc(ilosc);
        p.setCenaBruttoAktualna(cenaB);
        p.setCenaNettoAktualna(cenaN);
        p.setNazwa(nazwa);
        p.setProcentVat(vat);
        p.setOpis(opis);
        Kategoria k = kategoriaDao.getByName(kategoria);
        Producent producentp = producentDao.findByName(producent);
        kategoriaProducent.setKategoria(k);

        kategoriaProducent.setProducent(producentp);
        if (kategoriaProducentDao.getKP(kategoriaProducent.getProducent().getIdProducent(), kategoriaProducent.getKategoria().getIdKategoria()) == null) {
            kategoriaProducentDao.addKategoriaProducent(kategoriaProducent);
            kategoriaProducent = kategoriaProducentDao.getKP(kategoriaProducent.getProducent().getIdProducent(), kategoriaProducent.getKategoria().getIdKategoria());
        }
        p.setKategoriaProducent(kategoriaProducent);
        produktDao.addProdukt(p);
    }

    public int getIdzamowienia() {
        return idzamowienia;
    }

    public void setIdzamowienia(int idzamowienia) {
        this.idzamowienia = idzamowienia;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public List<String> getList() {
        return list;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public void setKategoria(Kategoria k) {
        this.category = k;
    }

    public Kategoria getcategory2() {
        return category;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getCenaB() {
        return cenaB;
    }

    public void setCenaB(float cenaB) {
        this.cenaB = cenaB;
    }

    public float getCenaN() {
        return cenaN;
    }

    public void setCenaN(float cenaN) {
        this.cenaN = cenaN;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

}
