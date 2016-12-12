/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AdresDao;
import DAO.KlientDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Adres;
import model.Klient;

/**
 *
 * @author Lukasz
 */
@ManagedBean
@RequestScoped
public class RegisterControll {
    private Klient customer = new Klient();
    private Adres adres = new Adres();
    private String pass;
    private KlientDao klientDao=new KlientDao();

    public void add() {
        if (!KlientDao.isExist(customer.getLogin())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Podany login już istnieje"));
        } else {
            if (customer.getHaslo().equals(pass)) {
                klientDao.addKlient(customer);
                customer = klientDao.getKlient(customer.getLogin());
                adres.setKlient(customer);
                AdresDao.addAdres(adres);

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hasła się nie zgadzają"));
            }

        }
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Klient getCustomer() {
        return customer;
    }

    public void setCustomer(Klient customer) {
        this.customer = customer;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
