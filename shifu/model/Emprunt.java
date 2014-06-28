package shifu.model;

import java.sql.Date;
import shifu.core.AbstractShifuModel;

public class Emprunt extends AbstractShifuModel {

    private int idEmprunt;
    private Date dateEmprunt;
    private int idExemplaire;
    private int idIndividu;
    private Adherent abonne;
    private Ouvrage ouvrage;
    private boolean isRenouvele = false;

    public Emprunt() {
    }

    public Emprunt( Date dateEmprunt, Adherent abonne, Ouvrage ouvrage ) {
        this.dateEmprunt = dateEmprunt;
        this.abonne = abonne;
        this.ouvrage = ouvrage;
    }

    public Emprunt( int idEmprunt, Date dateEmprunt, int idExemplaire, int idIndividu ) {
        this.idEmprunt = idEmprunt;
        this.dateEmprunt = dateEmprunt;
        this.idExemplaire = idExemplaire;
        this.idIndividu = idIndividu;
    }

    public Emprunt( int idEmprunt, Date dateEmprunt, Adherent abonne, Ouvrage ouvrage ) {
        this.idEmprunt = idEmprunt;
        this.dateEmprunt = dateEmprunt;
        this.abonne = abonne;
        this.ouvrage = ouvrage;
    }

    public boolean isIsRenouvele() {
        return isRenouvele;
    }

    public void setIsRenouvele( boolean isRenouvele ) {
        this.isRenouvele = isRenouvele;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt( Date value ) {
        dateEmprunt = value;
    }

    public Adherent getAbonne() {
        return abonne;
    }

    public void setAbonne( Adherent value ) {
        abonne = value;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage( Ouvrage value ) {
        ouvrage = value;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt( int idEmprunt ) {
        this.idEmprunt = idEmprunt;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire( int idExemplaire ) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdIndividu() {
        return idIndividu;
    }

    public void setIdIndividu( int idIndividu ) {
        this.idIndividu = idIndividu;
    }

    @Override
    public String toString() {
        return "Emprunt{" + "idEmprunt=" + idEmprunt + ", dateEmprunt=" + dateEmprunt + ", idExemplaire=" + idExemplaire + ", idIndividu=" + idIndividu + ", abonne=" + abonne + ", ouvrage=" + ouvrage + ", isRenouvele=" + isRenouvele + '}';
    }

}
