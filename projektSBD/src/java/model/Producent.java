package model;
// Generated 2016-12-05 21:50:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Producent generated by hbm2java
 */
public class Producent  implements java.io.Serializable {


     private Integer idProducent;
     private String nazwaProducenta;
     private Set kategoriaProducents = new HashSet(0);

    public Producent() {
    }

	
    public Producent(String nazwaProducenta) {
        this.nazwaProducenta = nazwaProducenta;
    }
    public Producent(String nazwaProducenta, Set kategoriaProducents) {
       this.nazwaProducenta = nazwaProducenta;
       this.kategoriaProducents = kategoriaProducents;
    }
   
    public Integer getIdProducent() {
        return this.idProducent;
    }
    
    public void setIdProducent(Integer idProducent) {
        this.idProducent = idProducent;
    }
    public String getNazwaProducenta() {
        return this.nazwaProducenta;
    }
    
    public void setNazwaProducenta(String nazwaProducenta) {
        this.nazwaProducenta = nazwaProducenta;
    }
    public Set getKategoriaProducents() {
        return this.kategoriaProducents;
    }
    
    public void setKategoriaProducents(Set kategoriaProducents) {
        this.kategoriaProducents = kategoriaProducents;
    }




}


