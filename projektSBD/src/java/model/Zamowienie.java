package model;
// Generated 2016-12-05 21:50:16 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Zamowienie generated by hbm2java
 */
public class Zamowienie  implements java.io.Serializable {


     private Integer nrZamowienia;
     private Adres adres;
     private Klient klient;
     private Date dataZlozenia;
     private Date dataPrzyjecia;
     private Date dataWysylki;
     private String stanZamowienia;
     private Set zamowienieProdukts = new HashSet(0);
     private Set zamowienieDostawcas = new HashSet(0);
     private Set fakturas = new HashSet(0);

    public Zamowienie() {
    }

	
    public Zamowienie(Adres adres, Date dataZlozenia, String stanZamowienia) {
        this.adres = adres;
        this.dataZlozenia = dataZlozenia;
        this.stanZamowienia = stanZamowienia;
    }
    public Zamowienie(Adres adres, Klient klient, Date dataZlozenia, Date dataPrzyjecia, Date dataWysylki, String stanZamowienia, Set zamowienieProdukts, Set zamowienieDostawcas, Set fakturas) {
       this.adres = adres;
       this.klient = klient;
       this.dataZlozenia = dataZlozenia;
       this.dataPrzyjecia = dataPrzyjecia;
       this.dataWysylki = dataWysylki;
       this.stanZamowienia = stanZamowienia;
       this.zamowienieProdukts = zamowienieProdukts;
       this.zamowienieDostawcas = zamowienieDostawcas;
       this.fakturas = fakturas;
    }
   
    public Integer getNrZamowienia() {
        return this.nrZamowienia;
    }
    
    public void setNrZamowienia(Integer nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }
    public Adres getAdres() {
        return this.adres;
    }
    
    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    public Klient getKlient() {
        return this.klient;
    }
    
    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    public Date getDataZlozenia() {
        return this.dataZlozenia;
    }
    
    public void setDataZlozenia(Date dataZlozenia) {
        this.dataZlozenia = dataZlozenia;
    }
    public Date getDataPrzyjecia() {
        return this.dataPrzyjecia;
    }
    
    public void setDataPrzyjecia(Date dataPrzyjecia) {
        this.dataPrzyjecia = dataPrzyjecia;
    }
    public Date getDataWysylki() {
        return this.dataWysylki;
    }
    
    public void setDataWysylki(Date dataWysylki) {
        this.dataWysylki = dataWysylki;
    }
    public String getStanZamowienia() {
        return this.stanZamowienia;
    }
    
    public void setStanZamowienia(String stanZamowienia) {
        this.stanZamowienia = stanZamowienia;
    }
    public Set getZamowienieProdukts() {
        return this.zamowienieProdukts;
    }
    
    public void setZamowienieProdukts(Set zamowienieProdukts) {
        this.zamowienieProdukts = zamowienieProdukts;
    }
    public Set getZamowienieDostawcas() {
        return this.zamowienieDostawcas;
    }
    
    public void setZamowienieDostawcas(Set zamowienieDostawcas) {
        this.zamowienieDostawcas = zamowienieDostawcas;
    }
    public Set getFakturas() {
        return this.fakturas;
    }
    
    public void setFakturas(Set fakturas) {
        this.fakturas = fakturas;
    }




}


