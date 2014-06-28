/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.tables;

import java.sql.Date;
import shifu.model.Ouvrage;

/**
 *
 * @author delwiv
 */
public class TableOeuvre {

    int idOeuvre;
    String titre;
    String titreOriginal;
    int note;
    Date datePublication;
    int idCouverture;
    String sousTitre;
    String langue;
    int idNationalitePaysOrigine;
    int idGenre;

    public TableOeuvre( int idOeuvre, String titre, String titreOriginal, int note, Date datePublication, int idCouverture, String sousTitre, String langue, int idNationalitePaysOrigine, int idGenre ) {
        this.idOeuvre = idOeuvre;
        this.titre = titre;
        this.titreOriginal = titreOriginal;
        this.note = note;
        this.datePublication = datePublication;
        this.idCouverture = idCouverture;
        this.sousTitre = sousTitre;
        this.langue = langue;
        this.idNationalitePaysOrigine = idNationalitePaysOrigine;
        this.idGenre = idGenre;
    }

    public TableOeuvre( Ouvrage ouvrage ) {
        this( ouvrage.getIdOeuvre(),
                ouvrage.getTitre(),
                ouvrage.getTitreOriginal(),
                ouvrage.getNote(),
                ouvrage.getDatePublication(),
                ouvrage.getIdCouverture(),
                ouvrage.getSousTitre(),
                ouvrage.getLangue(),
                ouvrage.getNationalite().getID(),
                ouvrage.getGenre().getIdGenre()
        );
    }

    public Ouvrage getOuvrage( Ouvrage ouvrage ) {
        ouvrage.setIdOeuvre( idOeuvre );
        ouvrage.setTitre( titre );
        ouvrage.setTitreOriginal( titreOriginal );
        ouvrage.setNote( note );
        ouvrage.setDatePublication( datePublication );
        ouvrage.setIdCouverture( idCouverture );
        ouvrage.setSousTitre( sousTitre );
        ouvrage.setLangue( langue );
        ouvrage.setIdNationalite( idNationalitePaysOrigine );

        return ouvrage;
    }

    /*   public Ouvrage getNewOuvrage() {
     return new Ouvrage( idOeuvre,
     0,
     null,
     titre,
     titreOriginal,
     note,
     genre,
     datePublication,
     null,
     idCouverture,
     sousTitre,
     langue,
     null,
     idNationalitePaysOrigine,
     null,
     null,
     null );
     }*/
    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre( String titre ) {
        this.titre = titre;
    }

    public String getTitreOriginal() {
        return titreOriginal;
    }

    public void setTitreOriginal( String titreOriginal ) {
        this.titreOriginal = titreOriginal;
    }

    public int getNote() {
        return note;
    }

    public void setNote( int note ) {
        this.note = note;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre( int idGenre ) {
        this.idGenre = idGenre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication( Date datePublication ) {
        this.datePublication = datePublication;
    }

    public int getIdCouverture() {
        return idCouverture;
    }

    public void setIdCouverture( int idCouverture ) {
        this.idCouverture = idCouverture;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre( String sousTitre ) {
        this.sousTitre = sousTitre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue( String langue ) {
        this.langue = langue;
    }

    public int getIdNationalitePaysOrigine() {
        return idNationalitePaysOrigine;
    }

    public void setIdNationalitePaysOrigine( int idNationalitePaysOrigine ) {
        this.idNationalitePaysOrigine = idNationalitePaysOrigine;
    }

}
