package shifu.model;

import shifu.core.AbstractShifuModel;
import java.sql.Date;

public class Individu extends AbstractShifuModel {

    protected int ID;
    protected String nom;
    protected String prenom;
    protected String titre;
    protected String email;
    protected String tel;
    protected Date dateNaissance;
    protected Adresse adresse;
    protected int idAdresse;
    protected Login login;
    protected int idLogin;

    public Individu() {
    }

    public Individu( int ID, String nom, String prenom, String titre, String email, String tel, Date dateNaissance ) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
    }

    public Individu( int ID, String nom, String prenom, String titre, String email, String tel, Date dateNaissance, int idAdresse, int idLogin ) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.idAdresse = idAdresse;
        this.idLogin = idLogin;
    }

    public Individu( int ID, String nom, String prenom, String titre, String email, String tel, Date dateNaissance, Adresse adresse, Login login ) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.login = login;
    }

    

    public int getID() {
        return ID;
    }

    public void setID( int ID ) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String value ) {
        nom = value;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String value ) {
        prenom = value;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre( String value ) {
        titre = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String value ) {
        email = value;
    }

    public String getTel() {
        return tel;
    }

    public void setTel( String value ) {
        tel = value;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance( Date value ) {
        dateNaissance = value;
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse( int idAdresse ) {
        this.idAdresse = idAdresse;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin( int idLogin ) {
        this.idLogin = idLogin;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse( Adresse adresse ) {
        this.adresse = adresse;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin( Login login ) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Individu{" + "ID=" + ID + ", nom=" + nom + ", prenom=" + prenom + ", titre=" + titre + ", email=" + email + ", tel=" + tel + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", idAdresse=" + idAdresse + ", login=" + login + ", idLogin=" + idLogin + '}';
    }

    
}
