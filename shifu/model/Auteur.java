package shifu.model;

import org.jdom2.Element;
import shifu.core.AbstractShifuModel;
import shifu.core.ISerialisableJSON;
import shifu.thirdparty.JSONObject;

public class Auteur extends AbstractShifuModel implements ISerialisableJSON {
    
    private int ID;
    private String nom;
    private String prenom;
    private String surnom;
    private String commentaire_auteur;
    private Nationalite nationalite;
    
    public Auteur() {
    }
    
    public Auteur( Element elemAuteur ) {
        this.nom = elemAuteur.getChildText( "LastName" );
        this.prenom = elemAuteur.getChildText( "FirstName" );
        this.surnom = elemAuteur.getChildText( "NickName" );
    }
    
    public Auteur( String nomComplet ) {
        this.prenom = nomComplet.split( " " )[0];
        this.nom = nomComplet.substring( this.prenom.length() - 1 );
    }
    
    public Auteur( String nom, String prenom, String surnom, String commentaire_auteur, Nationalite nationalite ) {
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.commentaire_auteur = commentaire_auteur;
        this.nationalite = nationalite;
    }
    
    public Auteur( int ID, String nom, String prenom, String surnom, String commentaire_auteur, Nationalite nationalite ) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.commentaire_auteur = commentaire_auteur;
        this.nationalite = nationalite;
    }
    
    public Auteur( int ID, String nom, String prenom, String surnom, String commentaire_auteur ) {
        this.ID = ID;
        if ( null == nom ) {
            nom = "";
        }
        if ( null == prenom ) {
            prenom = "";
        }
        if ( null == surnom ) {
            surnom = "";
        }
        if ( null == commentaire_auteur ) {
            commentaire_auteur = "";
        }
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.commentaire_auteur = commentaire_auteur;
    }
    
    public Auteur( JSONObject JSONIn ) {
        this.nom = readString( "text", JSONIn.getJSONObject( "LastName" ) );
        this.prenom = readString( "text", JSONIn.getJSONObject( "FirstName" ) );
        this.surnom = readString( "text", JSONIn.getJSONObject( "NickName" ) );
//        this.commentaire_auteur = commentaire_auteur;
    }
    
    @Override
    public int readInt( String param, JSONObject JSONIn ) {
        int result = 0;
        try {
            result = JSONIn.getInt( param );
        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
        return result;
    }
    
    @Override
    public String readString( String param, JSONObject JSONIn
    ) {
        String result = "";
        try {
            result = JSONIn.getString( param );
        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
        return result;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID( int ID ) throws Exception {
        this.ID = ID;
        notifyObservers();
    }
    
    public String getNom() {
        if ( null == nom ) {
            nom = "";
        }
        return nom;
    }
    
    public void setNom( String value ) {
        nom = value;
    }
    
    public String getPrenom() {
        if ( null == prenom ) {
            prenom = "";
        }
        return prenom;
    }
    
    public void setPrenom( String value ) {
        prenom = value;
    }
    
    public String getSurnom() {
        if ( null == surnom ) {
            surnom = "";
        }
        return surnom;
    }
    
    public void setSurnom( String value ) {
        surnom = value;
    }
    
    public String getCommentaire_auteur() {
        if ( null == commentaire_auteur ) {
            commentaire_auteur = "";
        }
        return commentaire_auteur;
    }
    
    public void setCommentaire_auteur( String value ) {
        commentaire_auteur = value;
    }
    
    public Nationalite getNationalite() {
        return nationalite;
    }
    
    public void setNationnalite( Nationalite value ) {
        nationalite = value;
    }
    
    @Override
    public String toString() {
        return "Auteur{" + "ID=" + ID + ", nom=" + nom + ", prenom=" + prenom + ", surnom=" + surnom + ", commentaire_auteur=" + commentaire_auteur + ", nationalite=" + nationalite + '}';
    }
    
    @Override
    public boolean equals( Object obj ) {
        Auteur auteur = ( Auteur ) obj;
        if ( nom.equals( auteur.getNom() )
                && prenom.equals( auteur.getPrenom() )
                && surnom.equals( auteur.getSurnom() )
                && nationalite.getID() == auteur.getNationalite().getID() ) {
            return true;
        }
        return false;
    }
    
}
