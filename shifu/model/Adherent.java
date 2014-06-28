package shifu.model;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;

public class Adherent extends Individu {

    private Date dateAdhesion;
    private List<Emprunt> listEmprunts = new ArrayList();

    public Adherent() {
    }

    public Adherent( Date dateAdhesion ) {
        this.dateAdhesion = dateAdhesion;
    }

    public Adherent( Date dateAdhesion, int idIndividu ) {
        this.dateAdhesion = dateAdhesion;
        this.ID = idIndividu;
    }

    public Adherent( Individu individu ) {
        this.ID = individu.ID;
        this.nom = individu.nom;
        this.prenom = individu.prenom;
        this.titre = individu.titre;
        this.email = individu.email;
        this.tel = individu.tel;
        this.dateNaissance = individu.dateNaissance;
        this.idAdresse = individu.idAdresse;
        this.idLogin = individu.idLogin;
    }

    public Adherent( Individu individu, Date dateAdhesion ) {
        this( individu );
        this.dateAdhesion = dateAdhesion;
    }

    public void removeEmprunt( Emprunt emprunt ) {
        ListIterator<Emprunt> it = listEmprunts.listIterator( 0 );
        while ( it.hasNext() ) {
            Emprunt e = it.next();
            if ( e.getIdEmprunt() == emprunt.getIdEmprunt() ) {
                listEmprunts.remove( e );
            }
        }
    }

    public Date getDateAdhesion() {
        if ( null == dateAdhesion ) {
            dateAdhesion = java.sql.Date.valueOf( "1970-01-01" );
        }
        return dateAdhesion;
    }

    public void setDateAdhesion( Date value ) {
        dateAdhesion = value;
    }

    public List<Emprunt> getEmprunts() {
        if (null == listEmprunts){
            listEmprunts = new ArrayList();
        }
        return listEmprunts;
    }

    public void setEmprunts( List<Emprunt> value ) {
        listEmprunts = value;
    }

    public int getIdIndividu() {
        return ID;
    }

    public void setIdIndividu( int idIndividu ) {
        this.ID = idIndividu;
    }

    @Override
    public String toString() {
        return "Adherent{ dateAdhesion=" + dateAdhesion + ", emprunts=" + listEmprunts + '}';
    }

    public boolean equals( Adherent adherent ) {
        if ( null != adherent ) {
            if ( this.nom == adherent.nom
                    && this.prenom == adherent.prenom
                    && this.titre == adherent.titre
                    && this.email == adherent.email
                    && this.tel == adherent.tel
                    && this.dateNaissance == adherent.dateNaissance ) {
                return true;
            }
        }
        return false;
    }

}
