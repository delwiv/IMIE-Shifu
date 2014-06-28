package shifu.model;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jdom2.Element;
import shifu.core.AbstractShifuModel;
import shifu.core.ISerialisableJSON;
import shifu.thirdparty.JSONArray;
import shifu.thirdparty.JSONObject;
import shifu.model.dao.tables.TableExemplaire;
import shifu.model.dao.tables.TableOeuvre;
import shifu.thirdparty.JSONException;

/**
 * Cette classe correspond en base à la table Oeuvre et à la table Exemplaire En
 * java nous considérons les termes métiers, donc un Ouvrage correspond à un
 * livre ou un dvd en rayon, prété ou autre, mais un objet réel.
 *
 * @author delwiv
 */
public class Ouvrage extends AbstractShifuModel implements ISerialisableJSON {

    protected int idOeuvre;
    protected int idExemplaire;
    protected String dispo;
    protected String titre;
    protected String titreOriginal;
    protected int note;
    protected Date datePublication;
    protected Image couverture;
    protected int idCouverture;
    protected String sousTitre;
    protected String langue;
    protected Nationalite nationalite;
    protected int idNationalite;
    protected List<Auteur> auteurs;
    protected List<IdentifiantOeuvre> identifiants;
    protected EtatOuvrage etat;
    protected int idEtat;
    protected String commentaire;
    protected List<Lien> liens;
    protected Genre genre;

    public Ouvrage() {
    }

    public Ouvrage( String dispo, String titre, String titreOriginal, int note, Date datePublication, Image couverture, int idCouverture, String sousTitre, String langue, Nationalite nationalite, int idNationalite, List<Auteur> auteurs, List<IdentifiantOeuvre> identifiants, EtatOuvrage etat, int idEtat, String commentaire, List<Lien> liens, Genre genre ) {
        this.dispo = dispo;
        this.titre = titre;
        this.titreOriginal = titreOriginal;
        this.note = note;
        this.datePublication = datePublication;
        this.couverture = couverture;
        this.idCouverture = idCouverture;
        this.sousTitre = sousTitre;
        this.langue = langue;
        this.nationalite = nationalite;
        this.idNationalite = idNationalite;
        this.auteurs = auteurs;
        this.identifiants = identifiants;
        this.etat = etat;
        this.idEtat = idEtat;
        this.commentaire = commentaire;
        this.liens = liens;
        this.genre = genre;
    }

    public Ouvrage( int idOeuvre, int idExemplaire, String dispo, String titre, String titreOriginal, int note, Date datePublication, Image couverture, int idCouverture, String sousTitre, String langue, Nationalite nationalite, int idNationalite, List<Auteur> auteurs, List<IdentifiantOeuvre> identifiants, EtatOuvrage etat, int idEtat, String commentaire, List<Lien> liens, Genre genre ) {
        this( dispo, titre, titreOriginal, note, datePublication, couverture, idCouverture, sousTitre, langue, nationalite, idNationalite, auteurs, identifiants, etat, idEtat, commentaire, liens, genre );
        this.idOeuvre = idOeuvre;
        this.idExemplaire = idExemplaire;
        this.dispo = dispo;
        this.titre = titre;
        this.titreOriginal = titreOriginal;
        this.note = note;
        this.datePublication = datePublication;
        this.couverture = couverture;
        this.idCouverture = idCouverture;
        this.sousTitre = sousTitre;
        this.langue = langue;
        this.nationalite = nationalite;
        this.idNationalite = idNationalite;
        this.auteurs = auteurs;
        this.identifiants = identifiants;
        this.etat = etat;
        this.idEtat = idEtat;
        this.commentaire = commentaire;
        this.liens = liens;
        this.genre = genre;
    }

    public Ouvrage( Element elemOuvrage ) {
//        System.out.println( elemOuvrage.toString() );
        List<Auteur> listAuteurs = new ArrayList();
        try {
            Element elemAuteurs = elemOuvrage.getChild( "Authors" );
            List listElemAuteur = elemAuteurs.getChildren( "Author" );
            ListIterator it = listElemAuteur.listIterator( 0 );
            while ( it.hasNext() ) {
                listAuteurs.add( new Auteur( ( Element ) it.next() ) );
            }
        } catch ( Exception ex ) {
            System.out.println( ex.toString() );
        }

        this.titre = elemOuvrage.getChildText( "Title" );
        this.titreOriginal = elemOuvrage.getChildText( "OriginalTitle" );
        try {
//            this.genre = new Genre( elemOuvrage.getChildText( "Category" ), 0, null, null );
        } catch ( Exception ex ) {

        }
        try {
            this.datePublication = Date.valueOf( elemOuvrage.getChildText( "PublishedYear" ) );
        } catch ( Exception ex ) {

        }
        this.identifiants = new ArrayList();
        try {
            this.identifiants.add( new IdentifiantOeuvre( 0, "ID ASSO", elemOuvrage.getChildText( "Location" ), 0 ) );
        } catch ( Exception ex ) {
//            ex.printStackTrace();
        }
        try {
            this.identifiants.add( new IdentifiantOeuvre( 0, "ISBN", elemOuvrage.getChildText( "Code" ), 0 ) );
        } catch ( Exception ex ) {
//            ex.printStackTrace();
        }

        setAuteurs( listAuteurs );
    }

    public Ouvrage( TableOeuvre tableOeuvre, Genre genre, List<IdentifiantOeuvre> listIdentifiants, Nationalite nationalite, List<Auteur> listAuteurs ) {
        this.idOeuvre = tableOeuvre.getIdOeuvre();
        this.titre = tableOeuvre.getTitre();
        this.titreOriginal = tableOeuvre.getTitreOriginal();
        this.note = tableOeuvre.getNote();
        this.datePublication = tableOeuvre.getDatePublication();
        this.sousTitre = tableOeuvre.getSousTitre();
        this.langue = tableOeuvre.getLangue();
        this.genre = genre;
        this.identifiants = listIdentifiants;
        this.nationalite = nationalite;
        this.auteurs = listAuteurs;

    }

    public Ouvrage( TableOeuvre tableOeuvre, TableExemplaire tableExemplaire, Genre genre ) {
        this.idOeuvre = tableOeuvre.getIdOeuvre();
        this.idExemplaire = tableExemplaire.getIdExemplaire();
        this.dispo = tableExemplaire.getDispo();
        this.titre = tableOeuvre.getTitre();
        this.titreOriginal = tableOeuvre.getTitreOriginal();
        this.note = tableOeuvre.getNote();
//        this.genre = tableOeuvre.getGenre();
        this.datePublication = tableOeuvre.getDatePublication();
//        this.couverture = tableOeuvre.;
        this.idCouverture = tableOeuvre.getIdCouverture();
        this.sousTitre = tableOeuvre.getSousTitre();
        this.langue = tableOeuvre.getLangue();
//        this.nationalite = nationalite;
        this.idNationalite = tableOeuvre.getIdNationalitePaysOrigine();
//        this.auteurs = auteurs;
//        this.identifiants = identifiants;
//        this.etat = etat;
        this.idEtat = idEtat;
        this.commentaire = tableExemplaire.getCommentaire();
    }

    public Ouvrage( TableOeuvre tableOeuvre, Genre genre, Auteur auteur, Nationalite nationaliteOeuvre ) {
        this.titre = tableOeuvre.getTitre();
        this.sousTitre = tableOeuvre.getSousTitre();
        this.titreOriginal = tableOeuvre.getTitreOriginal();
        //this.auteur = auteur.getNom();

    }

    public Ouvrage( JSONObject JSONIn ) {
        try {
            try {
                JSONArray authors = JSONIn.getJSONArray( "authors" );
                int nbAuthors = authors.length();

                if ( nbAuthors != 0 ) {
                    List<Auteur> listAuteurs = new ArrayList<Auteur>();

                    for ( int i = 0 ; i < authors.length() ; i++ ) {
                        System.out.println( authors.getString( i ) );
                        listAuteurs.add( new Auteur( authors.getString( i ) ) );

                    }
                    this.auteurs = listAuteurs;
                }
            } catch ( JSONException e ) {
//                System.out.println( e.toString() );
            }
            this.titre = readString( "title", JSONIn );
            // Google ne renvoie que l'année, je crée une date au 01/01 de cette année
            Date date = new Date( readInt( "publishedDate", JSONIn ), 1, 1 );
            this.datePublication = date;
            this.langue = readString( "language", JSONIn );
            this.sousTitre = readString( "subtitle", JSONIn );
            JSONArray jsIdentifiants = JSONIn.getJSONArray( "industryIdentifiers" );
            for ( int i = 0 ; i < jsIdentifiants.length() ; i++ ) {
                JSONObject id = jsIdentifiants.getJSONObject( i );

                this.identifiants.add( new IdentifiantOeuvre(
                        0,
                        readString( "type", id ),
                        readString( "identifier", id ),
                        0 ) );
            }

        } catch ( Exception e ) {
//            cout( e.toString() );
        }

    }

    @Override
    public String toString() {
        return "Ouvrage{" + "idOeuvre=" + idOeuvre + ", idExemplaire=" + idExemplaire + ", dispo=" + dispo + ", titre=" + titre + ", titreOriginal=" + titreOriginal + ", note=" + note + ", datePublication=" + datePublication + ", couverture=" + couverture + ", idCouverture=" + idCouverture + ", sousTitre=" + sousTitre + ", langue=" + langue + ", nationalite=" + nationalite + ", idNationalite=" + idNationalite + ", auteurs=" + auteurs + ", identifiants=" + identifiants + ", etat=" + etat + ", idEtat=" + idEtat + ", commentaire=" + commentaire + ", liens=" + liens + ", genre=" + genre + '}';
    }

    @Override
    public int readInt( String param, JSONObject JSONIn ) {
        int result = 0;
        try {
            result = JSONIn.getInt( param );
        } catch ( JSONException e ) {
//            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String readString( String param, JSONObject JSONIn ) {
        String result = "";
        try {
            result = JSONIn.getString( param );
        } catch ( JSONException e ) {
        }
        return result;
    }

    public String getIsbn() {
        String response = "Pas d'ISBN";
        ListIterator it = identifiants.listIterator( 0 );
        while ( it.hasNext() ) {
            IdentifiantOeuvre idOeuvre = ( IdentifiantOeuvre ) it.next();
            if ( idOeuvre.getLib().equals( "ISBN" ) ) {
                response = String.valueOf( idOeuvre.getValue() );
            }
        }
        return response;
    }

    public String getCodeInterne() {
        String response = "_";
        ListIterator it = identifiants.listIterator( 0 );
        while ( it.hasNext() ) {
            IdentifiantOeuvre idOeuvre = ( IdentifiantOeuvre ) it.next();
            if ( idOeuvre.getLib().equals( "ID ASSO" ) ) {
                if ( String.valueOf( idOeuvre.getValue() ).length() > 0 ) {
                    response = String.valueOf( idOeuvre.getValue() );
                }
            }
        }
        return response;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }

    public List<Lien> getLiens() {
        return liens;
    }

    public void setLiens( List<Lien> liens ) {
        this.liens = liens;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication( Date datePublication ) {
        this.datePublication = datePublication;
    }

    public Image getCouverture() {
        return couverture;
    }

    public void setCouverture( Image couverture ) {
        this.couverture = couverture;
    }

    public int getIdCouverture() {
        return idCouverture;
    }

    public void setIdCouverture( int idCouverture ) {
        this.idCouverture = idCouverture;
    }

    public String getSousTitre() {
        if ( null == sousTitre ) {
            sousTitre = "";
        }
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

    public Nationalite getNationalite() {
        return nationalite;
    }

    public void setNationalite( Nationalite nationalite ) {
        this.nationalite = nationalite;
        this.idNationalite = nationalite.getID();
    }

    public int getIdNationalite() {
        return idNationalite;
    }

    public void setIdNationalite( int idNationalite ) {
        this.idNationalite = idNationalite;
    }

    public List<Auteur> getAuteurs() {
        if ( null == auteurs ) {
            auteurs = new ArrayList();
        }
        return auteurs;
    }

    public void setAuteurs( List<Auteur> auteurs ) {
        this.auteurs = auteurs;
    }

    public List<IdentifiantOeuvre> getIdentifiants() {
        if ( null == identifiants ) {
            identifiants = new ArrayList();
        }
        return identifiants;
    }

    public void setIdentifiants( List<IdentifiantOeuvre> identifiants ) {
        this.identifiants = identifiants;
    }

    public EtatOuvrage getEtat() {
        return etat;
    }

    public void setEtat( EtatOuvrage etat ) {
        this.etat = etat;
    }

    public int getIdEtat() {
        return idEtat;
    }

    public void setIdEtat( int idEtat ) {
        this.idEtat = idEtat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire( String commentaire ) {
        this.commentaire = commentaire;
    }

    public String getStrAuteurs() {
        String response = "";
        if ( null != auteurs ) {
            ListIterator it = auteurs.listIterator( 0 );

            while ( it.hasNext() ) {
                Auteur a = ( Auteur ) it.next();
                response += a.getPrenom() + " " + a.getNom();
                if ( it.hasNext() ) {
                    response += ", ";
                }
            }
        }
        return response;

    }
}
