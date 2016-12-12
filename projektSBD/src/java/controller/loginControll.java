/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KlientDao;
import DAO.PracownikDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Klient;
import model.Pracownik;

/**
 *
 * @author Lukasz
 */
@ManagedBean
@SessionScoped
public class loginControll {
    private Klient current = new Klient();
    private Pracownik worker = new Pracownik();
    private KlientDao klientDao = new KlientDao();
    private PracownikDao pracownikDao = new PracownikDao();

    public String login, password;

    public void validate() {
        boolean valid = klientDao.checkLogin(login, password);
        if (valid) {
            setCurrent(klientDao.getKlient(login));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("WORKER " + current.getLogin()));

        } else {

            // return null;
            if (current.getImie() == null) {
                valid = pracownikDao.checkLogin(login, password);

                if (valid) {
                    worker = pracownikDao.getWorker(login);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("WORKER " + worker.getLogin()));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Zły login lub hasło"));
                }

            }
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Klient getCurrent() {
        return current;
    }

    public void setCurrent(Klient current) {
        this.current = current;
    }
}
