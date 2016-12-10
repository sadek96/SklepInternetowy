/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class registerController {

    private Klient customer = new Klient();
    private Adres adres = new Adres();
    private String pass;

    
    public void add() {
        if (!KlientDao.isExist(customer.getLogin())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Podany login już istnieje"));
        }
        if (customer.getHaslo().equals(pass)) {
            KlientDao.addKlient(customer);
            customer = KlientDao.getKlient(customer.getLogin());
            adres.setKlient(customer);
            AdresDao.addAdres(adres);

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
