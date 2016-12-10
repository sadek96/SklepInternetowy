/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OpiniaDao;
import DAO.ProduktDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Opinia;
import model.Produkt;
import model.Zdjecie;

/**
 *
 * @author Daniel
 */
@ManagedBean
@ViewScoped
public class ProduktController {

    ProduktDao produktDao;

    OpiniaDao opiniaDao;

    int produktId;

    String opis;
    byte ocena;

    /**
     * Creates a new instance of ProduktController
     */
    public ProduktController() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        produktId = Integer.parseInt(params.get("produktId"));
        produktDao = new ProduktDao();
        opiniaDao = new OpiniaDao();
    }

    public Produkt getProduktById() {
        return produktDao.getById(produktId);
    }

    public Zdjecie getProduktZdjecie() {
        return produktDao.getZdjecia(produktId).get(0);
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public ArrayList<Opinia> getOpinie() {
        return (ArrayList<Opinia>) opiniaDao.getAllByProduktId(produktId);
    }
    
     public boolean isOpinieEmpty() {
        return opiniaDao.getAllByProduktId(produktId).isEmpty();
    }

    public void saveOpinia() {
        Opinia opinia = new Opinia();
        if (!opis.equals("")) {
            opinia.setOcena(ocena);
            opinia.setNazwaUzytkownika("Anonim");
            opinia.setProdukt(getProduktById());
            opinia.setTresc(opis);

            Date date = new Date();
            opinia.setDataWystawienia(date);

            opiniaDao.add(opinia);
        }
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public byte getOcena() {
        return ocena;
    }

    public void setOcena(byte ocena) {
        this.ocena = ocena;
    }

}
