/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.tables;

/**
 *
 * @author delwiv
 */
public class TableAuteurOeuvre {

    private int idAuteur;
    private int idOeuvre;

    public TableAuteurOeuvre( int idAuteur, int idOeuvre ) {
        this.idAuteur = idAuteur;
        this.idOeuvre = idOeuvre;
    }

    @Override
    public String toString() {
        return "TableAuteurOeuvre{" + "idAuteur=" + idAuteur + ", idOeuvre=" + idOeuvre + '}';
    }
    
    

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur( int idAuteur ) {
        this.idAuteur = idAuteur;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }

    
}
