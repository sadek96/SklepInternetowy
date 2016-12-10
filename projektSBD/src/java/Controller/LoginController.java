/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KlientDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Klient;

/**
 *
 * @author Lukasz
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private Klient current = new Klient();

    public String login, password;

    public void validate() {
        boolean valid = KlientDao.checkLogin(login, password);
        if (valid) {
            setCurrent(KlientDao.getKlient(login));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Zły login lub hasło"));
        }
        // return null;
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
