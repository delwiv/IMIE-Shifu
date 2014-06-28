/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

/**
 *
 * @author antoine
 */
public class PointRetrait {
    private int ID;
    private String libPointRetrait;
    private String fourchetteDispoPointRetrait;
    private Adresse adresse;
    private int id_adresse;

    public PointRetrait(int ID, String libPointRetrait, String fourchetteDispoPointRetrait, Adresse adresse) {
        this.ID = ID;
        this.libPointRetrait = libPointRetrait;
        this.fourchetteDispoPointRetrait = fourchetteDispoPointRetrait;
        this.adresse = adresse;
    }

    /**
     * Constructeur pour DAO (avec attributs d'Entité)
     * @param ID
     * @param libPointRetrait
     * @param fourchetteDispoPointRetrait
     * @param id_adresse 
     */
    public PointRetrait(int ID, String libPointRetrait, String fourchetteDispoPointRetrait, int id_adresse) {
        this.ID = ID;
        this.libPointRetrait = libPointRetrait;
        this.fourchetteDispoPointRetrait = fourchetteDispoPointRetrait;
        this.id_adresse = id_adresse;
    }

    
    
    
    
    
        
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLibPointRetrait() {
        return libPointRetrait;
    }

    public void setLibPointRetrait(String libPointRetrait) {
        this.libPointRetrait = libPointRetrait;
    }

    public String getFourchetteDispoPointRetrait() {
        return fourchetteDispoPointRetrait;
    }

    public void setFourchetteDispoPointRetrait(String fourchetteDispoPointRetrait) {
        this.fourchetteDispoPointRetrait = fourchetteDispoPointRetrait;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    
}
