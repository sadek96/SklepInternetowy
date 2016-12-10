/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KategoriaDao;
import DAO.ProduktDao;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Kategoria;
import model.Produkt;
import model.Zdjecie;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class ProduktyController {
    
    ProduktDao produktDao;
    KategoriaDao kategoriaDao;
    
    Produkt produkt;
    
    String nazwaKategorii;
    
    int produktId;
    
    int startPage;
    int pageCount;
    int currentPage;
    int pageSize;
    
    

    /**
     * Creates a new instance of ProduktController
     */
    public ProduktyController() {
        nazwaKategorii = "Wszystkie";
        produktDao = new ProduktDao();
        kategoriaDao = new KategoriaDao();
        startPage = 1;
        currentPage = startPage;
        pageSize = 9;
        pageCount = produktDao.getPageCount(pageSize);
    }
    
    
    
    public List<Kategoria> getAllKategorie() {
        return kategoriaDao.getAll();
    }
    
    public List<Produkt> getAll() {
        return produktDao.getAll();
    }
    
    public List<Produkt> getCurrentPageProducts() {
        List<Produkt> produkty = produktDao.getPagedProducts(this.nazwaKategorii,this.currentPage, this.pageSize);
        pageCount=(produkty.size()+pageSize-1)/pageSize;
        return produkty;
    }
    
    public String choosePage() {
        Map<String, String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.currentPage = Integer.parseInt(params.get("page"));
        return "products.xhtml";
    }
    
    public Zdjecie getProduktZdjecie(int idProduktu) {
        return produktDao.getZdjecia(idProduktu).get(0);
    }
    
    public boolean isHasNextPage() {
        if (currentPage < pageCount) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isHasPreviousPage() {
        if (currentPage > startPage) {
            return true;
        }
        return false;
    }
    
    public String nextPage(){
        if(isHasNextPage()){
            this.currentPage++;
            return "products.xhtml";
        }
        return null;
    }
    
    public String previousPage(){
        if(isHasPreviousPage()){
            this.currentPage--;
            return "products.xhtml";
        }
        return null;
    }
    
    public int getPageCount() {
        List<Produkt> produkty = produktDao.getProductsWithCategory(this.nazwaKategorii);
        pageCount=(produkty.size()+pageSize-1)/pageSize;
        return pageCount;
    }
    
    public Produkt getProdukt() {
        return produkt;
    }
    
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }
    
    public int getStartPage() {
        return startPage;
    }
    
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public String getNazwaKategorii() {
        return nazwaKategorii;
    }
    
    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }
    
    public int getProduktId() {
        return produktId;
    }
    
    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }
    
}
