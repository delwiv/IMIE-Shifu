/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model;

import java.util.ArrayList;
import java.util.List;
import shifu.core.AbstractShifuModel;
import shifu.core.Singleton;

/**
 * A n'utiliser qu'en Signleton avec Mediatheque mediatheque =
 * Singleton.getInstance(Mediatheque.class);
 *
 * @author delwiv
 */
public class Mediatheque extends AbstractShifuModel {

    List<Individu> listIndividu;
    List<Adherent> listAdherents;
    List<Ouvrage> listOuvrage;
    List<PointRetrait> listPointRetrait;
    List<Genre> listGenre;

    List<Auteur> listAuteursRecherche;
    List<Individu> listIndividuRecherche;
    List<Adherent> listAdherentsRecherche;

    List<Ouvrage> listOuvrageRecherche;

    public Mediatheque() {
        listIndividu = new ArrayList();
        listOuvrage = new ArrayList();
        listPointRetrait = new ArrayList();
        listOuvrageRecherche = new ArrayList();
        listIndividuRecherche = new ArrayList();
    }

    // On your nose
    public static Mediatheque getInstance() {
        return Singleton.getInstance( Mediatheque.class );
    }

    public List<Individu> getListIndividu() {
        return listIndividu;
    }

    public void setListIndividu( List<Individu> listIndividu ) throws Exception {
        this.listIndividu = listIndividu;
        notifyObservers();
    }

    public List<Ouvrage> getListOuvrage() {
        return listOuvrage;
    }

    public void setListOuvrage( List<Ouvrage> listOuvrage ) throws Exception {
        this.listOuvrage = listOuvrage;
        notifyObservers();
    }

    public List<PointRetrait> getListPointRetrait() {
        return listPointRetrait;
    }

    public void setListPointRetrait( List<PointRetrait> listPointRetrait ) throws Exception {
        this.listPointRetrait = listPointRetrait;
        notifyObservers();
    }

    public List<Individu> getListIndividuRecherche() {
        return listIndividuRecherche;
    }

    public List<Ouvrage> getListOuvrageRecherche() {
        return listOuvrageRecherche;
    }

    public void setListIndividuRecherche( List<Individu> listIndividuRecherche ) throws Exception {
        this.listIndividuRecherche = listIndividuRecherche;
        notifyObservers();
    }

    public void setListOuvrageRecherche( List<Ouvrage> listOuvrageRecherche ) throws Exception {
        this.listOuvrageRecherche = listOuvrageRecherche;
        notifyObservers();
    }

    public List<Auteur> getListAuteursRecherche() {
        return listAuteursRecherche;
    }

    public void setListAuteursRecherche( List<Auteur> listAuteursRecherche ) throws Exception {
        this.listAuteursRecherche = listAuteursRecherche;
        notifyObservers();
    }

    public List<Adherent> getListAdherents() {
        return listAdherents;
    }

    public void setListAdherents( List<Adherent> listAdherents ) throws Exception {
        this.listAdherents = listAdherents;
        notifyObservers();
    }

    public List<Adherent> getListAdherentsRecherche() {
        return listAdherentsRecherche;
    }

    public void setListAdherentsRecherche( List<Adherent> listAdherentsRecherche ) throws Exception {
        this.listAdherentsRecherche = listAdherentsRecherche;
        notifyObservers();
    }

    public void addAdherent( Adherent adherent ) throws Exception {
        this.listAdherents.add( adherent );
        notifyObservers();
    }

    public List<Genre> getListGenre() {
        return listGenre;
    }

    public void setListGenre( List<Genre> listGenre ) throws Exception {
        this.listGenre = listGenre;
        notifyObservers();
    }

}
