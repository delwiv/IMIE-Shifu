/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model;

import shifu.core.AbstractShifuModel;

/**
 *
 * @author antoine
 */
public class Adresse extends AbstractShifuModel {

    private int ID_adresse;
    private String ligne1_adresse;
    private String ligne2_adresse;
    private String ligne3_adresse;
    private String CP_adresse;
    private String ville_adresse;
    private String lien_gmaps_adresse;

    public Adresse( int ID_adresse, String ligne1_adresse, String ligne2_adresse, String ligne3_adresse, String CP_adresse, String ville_adresse, String lien_gmaps_adresse ) {
        this.ID_adresse = ID_adresse;
        this.ligne1_adresse = ligne1_adresse;
        this.ligne2_adresse = ligne2_adresse;
        this.ligne3_adresse = ligne3_adresse;
        this.CP_adresse = CP_adresse;
        this.ville_adresse = ville_adresse;
        this.lien_gmaps_adresse = lien_gmaps_adresse;
    }

    public Adresse( String ligne1_adresse, String ligne2_adresse, String ligne3_adresse, String CP_adresse, String ville_adresse, String lien_gmaps_adresse ) {
        this.ligne1_adresse = ligne1_adresse;
        this.ligne2_adresse = ligne2_adresse;
        this.ligne3_adresse = ligne3_adresse;
        this.CP_adresse = CP_adresse;
        this.ville_adresse = ville_adresse;
        this.lien_gmaps_adresse = lien_gmaps_adresse;
    }

      public Adresse( String ligne1_adresse, String ligne2_adresse, String ligne3_adresse, String CP_adresse, String ville_adresse ) {
        this.ligne1_adresse = ligne1_adresse;
        this.ligne2_adresse = ligne2_adresse;
        this.ligne3_adresse = ligne3_adresse;
        this.CP_adresse = CP_adresse;
        this.ville_adresse = ville_adresse;
        
    }
    
    
    public int getID_adresse() {
        return ID_adresse;
    }

    public void setID_adresse( int ID_adresse ) {
        this.ID_adresse = ID_adresse;
    }

    public String getLigne1_adresse() {
        return ligne1_adresse;
    }

    public void setLigne1_adresse( String ligne1_adresse ) {
        this.ligne1_adresse = ligne1_adresse;
    }

    public String getLigne2_adresse() {
        return ligne2_adresse;
    }

    public void setLigne2_adresse( String ligne2_adresse ) {
        this.ligne2_adresse = ligne2_adresse;
    }

    public String getLigne3_adresse() {
        return ligne3_adresse;
    }

    public void setLigne3_adresse( String ligne3_adresse ) {
        this.ligne3_adresse = ligne3_adresse;
    }

    public String getCP_adresse() {
        return CP_adresse;
    }

    public void setCP_adresse( String CP_adresse ) {
        this.CP_adresse = CP_adresse;
    }

    public String getVille_adresse() {
        return ville_adresse;
    }

    public void setVille_adresse( String ville_adresse ) {
        this.ville_adresse = ville_adresse;
    }

    public String getLien_gmaps_adresse() {
        return lien_gmaps_adresse;
    }

    public void setLien_gmaps_adresse( String lien_gmaps_adresse ) {
        this.lien_gmaps_adresse = lien_gmaps_adresse;
    }

}
