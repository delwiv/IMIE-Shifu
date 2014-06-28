package shifu.model;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import shifu.thirdparty.JSONArray;
import shifu.thirdparty.JSONObject;
import shifu.model.dao.tables.TableExemplaire;
import shifu.model.dao.tables.TableLivre;
import shifu.model.dao.tables.TableOeuvre;

public class Livre extends Ouvrage {

    private String collection;
    private String format;
    private String maisonEdition;
    private String resume;
    private int nbPages;

    public Livre() {
    }

    public Livre( String collection, String format, String maisonEdition, String resume, int nbPages, String dispo, String titre, String titreOriginal, int note, Date datePublication, Image couverture, int idCouverture, String sousTitre, String langue, Nationalite nationalite, int idNationalite, List<Auteur> auteurs, List<IdentifiantOeuvre> identifiants, EtatOuvrage etat, int idEtat, String commentaire, List<Lien> liens, Genre genre ) {
        super( dispo, titre, titreOriginal, note, datePublication, couverture, idCouverture, sousTitre, langue, nationalite, idNationalite, auteurs, identifiants, etat, idEtat, commentaire, liens, genre );
        this.collection = collection;
        this.format = format;
        this.maisonEdition = maisonEdition;
        this.resume = resume;
        this.nbPages = nbPages;
    }

    public Livre( Element elemLivre ) {
        super( elemLivre );
        this.collection = elemLivre.getChildText( "Collection" );
        this.format = elemLivre.getChildText( "Format" );
        this.maisonEdition = elemLivre.getChildText( "Publisher" );
        this.resume = elemLivre.getChildText( "Comments" );
        try {
            this.nbPages = Integer.parseInt( elemLivre.getChildText( "PagesNb" ) );
        } catch ( Exception ex ) {

        }
    }

    public Livre( JSONObject JSONIn ) {
        super( JSONIn );
        this.collection = readString( "Collection", JSONIn );
        this.format = readString( "Format", JSONIn );
        this.maisonEdition = readString( "Publisher", JSONIn );
        this.resume = readString( "Comments", JSONIn );
        this.nbPages = readInt( "PagesNb", JSONIn );

    }

    public Livre( TableOeuvre tableOeuvre, TableLivre tableLivre, TableExemplaire tableExemplaire ) {
//        super( tableOeuvre, tableExemplaire );
        this.collection = tableLivre.getCollection();
        this.format = tableLivre.getFormat();
        this.maisonEdition = tableLivre.getMaisonEdition();
        this.resume = tableLivre.getResume();
        this.nbPages = tableLivre.getNbPages();

    }

    public static List<Livre> getLivresFromJSON( int typeJson, JSONObject jsLivres ) {
        List<Livre> listLivre = new ArrayList();
        JSONArray arrayLivre = jsLivres.getJSONArray( "Book" );

        int nbLivre = arrayLivre.length();
//            System.out.println(arrayLivre.toString(4));

        for ( int i = 0 ; i < nbLivre ; i++ ) {
//                System.out.println("Boucle n°     " + (i + 1) + "/" + nbLivre);
            try {
                listLivre.add( new Livre( arrayLivre.getJSONObject( i ) ) );
//                    cout(listLivre.get(i).toString());

            } catch ( Exception e ) {
                e.printStackTrace();
            }

        }
//        }
//        cout("fin deserialisation Ouvrage\nNombre de Ouvrage : " + listOuvrage.size());

        return listLivre;
    }

    public void update( TableLivre tableLivre ) {
        if ( null != tableLivre ) {
            this.collection = tableLivre.getCollection();
            this.format = tableLivre.getFormat();
            this.maisonEdition = tableLivre.getMaisonEdition();
            this.resume = tableLivre.getResume();
            this.nbPages = tableLivre.getNbPages();
            this.idOeuvre = tableLivre.getIdOeuvre();
        }
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection( String collection ) {
        this.collection = collection;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition( String maisonEdition ) {
        this.maisonEdition = maisonEdition;
    }

    public String getResume() {
        return resume;
    }

    public void setResume( String resume ) {
        this.resume = resume;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages( int nbPages ) {
        this.nbPages = nbPages;
    }

}
