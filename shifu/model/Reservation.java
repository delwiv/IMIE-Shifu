/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

import java.sql.Date;

/**
 * Cette classe est une représentation objet de la table Réservation
 * @author antoine
 */
public class Reservation {
    private int ID;
    private Date dateReservation;
    private int idIndividu;
    private int idExemplaire;
    private int idPointRetrait;

    
    
        
    public Reservation(int ID, Date dateReservation, int idIndividu, int idExemplaire, int idPointRetrait) {
        this.ID = ID;
        this.dateReservation = dateReservation;
        this.idIndividu = idIndividu;
        this.idExemplaire = idExemplaire;
        this.idPointRetrait = idPointRetrait;
    }

    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdIndividu() {
        return idIndividu;
    }

    public void setIdIndividu(int idIndividu) {
        this.idIndividu = idIndividu;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdPointRetrait() {
        return idPointRetrait;
    }

    public void setIdPointRetrait(int idPointRetrait) {
        this.idPointRetrait = idPointRetrait;
    }
    
    
}
