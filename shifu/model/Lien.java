/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

/**
 *
 * @author delwiv
 */
public class Lien {
    private int id;
    private String nom;
    private String url;

    public Lien( int id, String nom, String url ) {
        this.id = id;
        this.nom = nom;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }
    

}
