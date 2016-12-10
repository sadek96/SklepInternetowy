/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KategoriaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Kategoria;

/**
 *
 * @author Daniel
 */

@ManagedBean
@SessionScoped
public class KategoriaController {
    
    KategoriaDao kategoriaDao;
    
    Kategoria kategoria;
    
    public List<Kategoria> getAllKategorie(){
        kategoriaDao = new KategoriaDao();
        return kategoriaDao.getAll();
    }
    
    public Kategoria getKategoriaId1(){
        kategoria = kategoriaDao.getById(1);
        return kategoria;
    }
    
    public Kategoria getKategoria(){
        return this.kategoria;
    }
    
}
