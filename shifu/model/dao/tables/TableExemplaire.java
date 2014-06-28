/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.tables;

import shifu.model.Ouvrage;

/**
 *
 * @author delwiv
 */
public class TableExemplaire {

    int idExemplaire;
    String dispo;
    String commentaire;
    int idOeuvre;
    int idEtat;

    public TableExemplaire( int idExemplaire, String dispo, String commentaire, int idOeuvre, int idEtat ) {
        this.idExemplaire = idExemplaire;
        this.dispo = dispo;
        this.commentaire = commentaire;
        this.idOeuvre = idOeuvre;
        this.idEtat = idEtat;
    }

    public TableExemplaire( Ouvrage ouvrage ) {
        int idEtat;
        try {
            idEtat = ouvrage.getEtat().getIdEtatOuvrage();
        } catch ( Exception e ) {
            idEtat = 0;
        }
        this.idExemplaire = ouvrage.getIdExemplaire();
        this.dispo = ouvrage.getDispo();
        this.commentaire = ouvrage.getCommentaire();
        this.idOeuvre = ouvrage.getIdOeuvre();
        this.idEtat = idEtat;

    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire( int idExemplaire ) {
        this.idExemplaire = idExemplaire;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo( String dispo ) {
        this.dispo = dispo;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire( String commentaire ) {
        this.commentaire = commentaire;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }

    public int getIdEtat() {
        return idEtat;
    }

    public void setIdEtat( int idEtat ) {
        this.idEtat = idEtat;
    }

}
