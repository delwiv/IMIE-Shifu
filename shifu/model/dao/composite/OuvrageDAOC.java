/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.composite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import shifu.thirdparty.JSONArray;
import shifu.thirdparty.JSONObject;
import shifu.model.Auteur;
import shifu.model.EtatOuvrage;
import shifu.model.Genre;
import shifu.model.IdentifiantOeuvre;
import shifu.model.Nationalite;
import shifu.model.Ouvrage;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.ShifuDAOFactory;
import shifu.model.dao.tables.TableAuteurOeuvre;
import shifu.model.dao.tables.TableExemplaire;
import shifu.model.dao.tables.TableLivre;
import shifu.model.dao.tables.TableOeuvre;

/**
 * Cette classe va utiliser un Ouvrage en paramètre (voir <link>Ouvrage</link>)
 *
 * @author delwiv
 */
public class OuvrageDAOC extends DAO<Ouvrage> {

    @Override
    public Ouvrage create( Ouvrage ouvrage ) throws SQLException {
        // create Oeuvre (
//            nationalite 
//            Oeuvre
//            auteur_oeuvre
//            identifiant)
        // create Exemplaire (
//            etat
//            Exemplaire)

        ListIterator it = null;

        // Ajout de la nationalite si elle n'existe pas déjà
        Nationalite nationalite = ouvrage.getNationalite();
        if ( null == nationalite ) {
            nationalite = new Nationalite( 0, "Inconnu", "INC" );
            ouvrage.setNationalite( nationalite );
        }
        List<Nationalite> listNationaliteCheck = ShifuDAOFactory.getNationaliteDAO()
                .getByLikeCustomArgument( new CustomArgument( "lib_Nationalite", nationalite.getLib() ) );

        it = listNationaliteCheck.listIterator( 0 );
        if ( it.hasNext() ) {
            Nationalite cur = ( Nationalite ) it.next();
            nationalite.setID( cur.getID() );

        }
        // Si pas d'ID alors il faut créer en base
        if ( nationalite.getID() == 0 ) {
            nationalite = ShifuDAOFactory.getNationaliteDAO().create( nationalite );
        }
        ouvrage.setNationalite( nationalite );

        // Gestion du genre
        if ( null == ouvrage.getGenre() ) {
            //Si pas de genre on vérifie en base la correspondance suivante :
            // Ouvrage.codeInterne.substring(0,2) = Genre.lib.substring(0,2)
            String codeInterne = ouvrage.getCodeInterne();
            System.out.println( "Code interne : " + codeInterne );
            if ( !codeInterne.equals( "_" ) ) {
                Genre genre = ShifuDAOFactory.getGenreDAO()
                        .getByLikeCustomArgument( new CustomArgument( "lib_genre", codeInterne.substring( 0, 3 ) + "%" ) )
                        .get( 0 );
                if ( null != genre ) {
                    System.out.println( "Genre lu : " + genre.toString() );
                    // si j'ai une réponse
                    ouvrage.setGenre( genre );
                } else {
                    //sinon on met genre inconnu
                    ouvrage.setGenre( ShifuDAOFactory.getGenreDAO().getGenreInconnu() );
                }
            } else {
                ouvrage.setGenre( ShifuDAOFactory.getGenreDAO().getGenreInconnu() );
            }

        }
        //ajout Oeuvre
        TableOeuvre tableOeuvre = new TableOeuvre( ouvrage );
        tableOeuvre = ShifuDAOFactory.getOeuvreDAO().create( tableOeuvre );

        ouvrage.setIdOeuvre( tableOeuvre.getIdOeuvre() );

        //ajout Auteur_Oeuvre
        List<Auteur> listAuteur = ouvrage.getAuteurs();

        boolean createAuthor = true;
        if ( listAuteur.isEmpty() ) {
            listAuteur.add( ShifuDAOFactory.getAuteurDAO().getAuteurInconnu() );
            createAuthor = false;
        }

        if ( createAuthor ) {
            it = listAuteur.listIterator( 0 );

            while ( it.hasNext() ) {
                Auteur auteur = ( Auteur ) it.next();
                auteur = ShifuDAOFactory.getAuteurDAO().create( auteur );
                TableAuteurOeuvre tableAuteurOeuvre = new TableAuteurOeuvre( auteur.getID(), ouvrage.getIdOeuvre() );
                ShifuDAOFactory.getAuteurOeuvreDAO().create( tableAuteurOeuvre );
            }
        }

        // Ajout des identifiants
        List<IdentifiantOeuvre> listIdOeuvre = ouvrage.getIdentifiants();
//        List<IdentifiantOeuvre> listIdOeuvreAdded = new ArrayList();

        it = listIdOeuvre.listIterator( 0 );

        while ( it.hasNext() ) {
            IdentifiantOeuvre idOeuvre = ( IdentifiantOeuvre ) it.next();
            idOeuvre.setIdOeuvre( ouvrage.getIdOeuvre() );
//            listIdOeuvreAdded.add( ShifuDAOFactory.getIdentifiantOeuvreDAO().create( idOeuvre ) );
            idOeuvre = ShifuDAOFactory.getIdentifiantOeuvreDAO().create( idOeuvre );
        }
//        ouvrage.setIdentifiants( listIdOeuvre );

        EtatOuvrage etatOuvrage = ouvrage.getEtat();
        if ( null == etatOuvrage ) {
            etatOuvrage = new EtatOuvrage( 0, "Bon etat", "Bon", true );
            ouvrage.setEtat( etatOuvrage );
        } else {

            ouvrage.getEtat()
                    .setIdEtatOuvrage( ShifuDAOFactory.getEtatOuvrageDAO()
                            .create( etatOuvrage ).getIdEtatOuvrage() );
        }
        //ajout Exemplaire

        TableExemplaire tableExemplaire = new TableExemplaire( ouvrage );
        tableExemplaire = ShifuDAOFactory.getExemplaireDAO().create( tableExemplaire );

        ouvrage.setIdExemplaire( tableExemplaire.getIdExemplaire() );

        return ouvrage;
    }

    public List<Ouvrage> getAllFromDB() throws SQLException {
        List<Ouvrage> listOuvrages = new ArrayList();
        Ouvrage ouvrage = null;

        // On recupere la ligne dans la table Oeuvre
        List<TableOeuvre> listTableOeuvre = ShifuDAOFactory.getOeuvreDAO().getByEqualsCustomArgument( new CustomArgument( "1", "1" ) );
        // si il y a bien une réponse
        for ( ListIterator it = listTableOeuvre.listIterator( 0 ) ; it.hasNext() ; ) {
            TableOeuvre tableOeuvre = ( TableOeuvre ) it.next();

            if ( tableOeuvre.getIdOeuvre() > 0 ) {
                // on récupère le genre
                Genre genre = ShifuDAOFactory.getGenreDAO().getByID( tableOeuvre.getIdGenre() );
                // les identifiants
                List<IdentifiantOeuvre> listIdentifiants = ShifuDAOFactory.getIdentifiantOeuvreDAO()
                        .getByEqualsCustomArgument( new CustomArgument( "ID_oeuvre", tableOeuvre.getIdOeuvre() ) );
                // la nationalite
                Nationalite nationalite = ShifuDAOFactory.getNationaliteDAO()
                        .getByID( tableOeuvre.getIdNationalitePaysOrigine() );
                // la liste des ID auteurs
                List<TableAuteurOeuvre> auteursOeuvre = ShifuDAOFactory.getAuteurOeuvreDAO()
                        .getByEqualsCustomArgument( new CustomArgument( "ID_oeuvre", tableOeuvre.getIdOeuvre() ) );

                // pour chaque ID on extrait l'auteur
                List<Auteur> listAuteurs = new ArrayList();
                ListIterator it2 = auteursOeuvre.listIterator( 0 );
                while ( it2.hasNext() ) {
                    TableAuteurOeuvre auteurOeuvre = ( TableAuteurOeuvre ) it2.next();
                    listAuteurs.add( ShifuDAOFactory.getAuteurDAO().getByID( auteurOeuvre.getIdAuteur() ) );
                }
                TableExemplaire tableExemplaire;
                try {
                    tableExemplaire = ShifuDAOFactory.getExemplaireDAO()
                            .getByEqualsCustomArgument( new CustomArgument( "ID_oeuvre", tableOeuvre.getIdOeuvre() ) )
                            .get( ( 0 ) );
                } catch ( Exception ex ) {
                    tableExemplaire = new TableExemplaire( ouvrage );
                }
                ouvrage = new Ouvrage( tableOeuvre, genre, listIdentifiants, nationalite, listAuteurs );
                ouvrage.setIdExemplaire( tableExemplaire.getIdOeuvre() );
                listOuvrages.add( ouvrage );
            }
        }
        return listOuvrages;
    }

    /**
     * On utilise l'ID de l'oeuvre
     *
     * @param ID ID de l'oeuvre
     * @return
     */
    @Override
    public Ouvrage getByID( int ID ) throws SQLException {
        Ouvrage ouvrage = null;

        // On recupere la ligne dans la table Oeuvre
        TableOeuvre tableOeuvre = ShifuDAOFactory.getOeuvreDAO().getByID( ID );
        // si il y a bien une réponse
        if ( tableOeuvre.getIdOeuvre() > 0 ) {
            // d'abord l'exemplaire (1ere réponse)
            TableExemplaire tableExemplaire = ShifuDAOFactory.getExemplaireDAO()
                    .getByEqualsCustomArgument(
                            new CustomArgument( "ID_oeuvre", tableOeuvre.getIdOeuvre() )
                    ).get( 0 );
            // ensuite l'état
            EtatOuvrage etatOuvrage = ShifuDAOFactory.getEtatOuvrageDAO()
                    .getByID( tableExemplaire.getIdEtat() );
            // on récupère le genre
            Genre genre = ShifuDAOFactory.getGenreDAO().getByID( tableOeuvre.getIdGenre() );
            // les identifiants
            List<IdentifiantOeuvre> listIdentifiants = ShifuDAOFactory.getIdentifiantOeuvreDAO()
                    .getByEqualsCustomArgument( new CustomArgument( "ID_oeuvre", ID ) );
            // la nationalite
            Nationalite nationalite = ShifuDAOFactory.getNationaliteDAO()
                    .getByID( tableOeuvre.getIdNationalitePaysOrigine() );
            // la liste des ID auteurs
            List<TableAuteurOeuvre> auteursOeuvre = ShifuDAOFactory.getAuteurOeuvreDAO()
                    .getByEqualsCustomArgument( new CustomArgument( "ID_oeuvre", ID ) );
            // pour chaque ID on extrait l'auteur
            List<Auteur> listAuteurs = new ArrayList();
            ListIterator it = auteursOeuvre.listIterator( 0 );
            while ( it.hasNext() ) {
                TableAuteurOeuvre auteurOeuvre = ( TableAuteurOeuvre ) it.next();
                listAuteurs.add( ShifuDAOFactory.getAuteurDAO().getByID( auteurOeuvre.getIdAuteur() ) );
            }
            ouvrage = new Ouvrage( tableOeuvre, genre, listIdentifiants, nationalite, listAuteurs );
            ouvrage.setEtat( etatOuvrage );
        }
        return ouvrage;
    }

    @Override
    public Ouvrage update( Ouvrage object ) {

        return object;
    }

    @Override
    public List<Ouvrage> getByEqualsCustomArgument( CustomArgument argument ) {
        List<Ouvrage> ouvrage = null;

        return ouvrage;
    }

    /**
     * Recherche d'ouvrage : on va chercher à différents endroits pour matcher
     * l'argument : Oeuvre.titre_oeuvre Oeuvre.titre_original_oeuvre
     * Genre.lib_genre Identifiant_oeuvre.valeur_identifiant_oeuvre
     * Auteur.nom_auteur Auteur.prenom_auteur Auteur.surnom_auteur
     * Livre.collection_livre Livre.maison_edition_livre Livre.resume_livre
     *
     * @param argument
     * @return
     */
    @Override
    public List<Ouvrage> getByLikeCustomArgument( CustomArgument argument ) throws SQLException {
        List<Ouvrage> listOuvrages = new ArrayList();
        ListIterator it = null;
        String searchArg = ( String ) argument.getValue();

        // on commence par la table oeuvre
        List<TableOeuvre> listTablesOeuvre = ShifuDAOFactory.getOeuvreDAO().
                getByLikeCustomArgument( new CustomArgument( "titre_oeuvre", searchArg ) );

        //idem avec le titre original (on ajoute les réponses à la liste)
        listTablesOeuvre.addAll( ShifuDAOFactory.getOeuvreDAO()
                .getByLikeCustomArgument( new CustomArgument( "titre_original_oeuvre", searchArg ) ) );

        // on récupère chaque ouvrage par son ID
        it = listTablesOeuvre.listIterator( 0 );
        while ( it.hasNext() ) {
            TableOeuvre tableOeuvre = ( TableOeuvre ) it.next();
            int idOuvrage = tableOeuvre.getIdOeuvre();
            listOuvrages.add( getByID( idOuvrage ) );
        }

        //Auteur
        List<Auteur> listAuteurs = ShifuDAOFactory.getAuteurDAO()
                .getByLikeCustomArgument( new CustomArgument( "nom_auteur", searchArg ) );
        listAuteurs.addAll( ShifuDAOFactory.getAuteurDAO()
                .getByLikeCustomArgument( new CustomArgument( "prenom_auteur", searchArg ) ) );
        listAuteurs.addAll( ShifuDAOFactory.getAuteurDAO()
                .getByLikeCustomArgument( new CustomArgument( "surnom_auteur", searchArg ) ) );

        it = listAuteurs.listIterator( 0 );
        while ( it.hasNext() ) {
            Auteur auteur = ( Auteur ) it.next();
            // On recupere les lignes dans Auteur_oeuvre
            List<TableAuteurOeuvre> listAuteurOeuvres = ShifuDAOFactory.getAuteurOeuvreDAO()
                    .getByEqualsCustomArgument( new CustomArgument( "ID_auteur", auteur.getID() ) );

            ListIterator it2 = listAuteurOeuvres.listIterator( 0 );
            // Pour chaque ligne on ajout l'Ouvrage            
            while ( it2.hasNext() ) {
                TableAuteurOeuvre current = ( TableAuteurOeuvre ) it2.next();
                listOuvrages.add( getByID( current.getIdOeuvre() ) );
            }
        }

        return listOuvrages;
    }

    @Override
    protected List<Ouvrage> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        throw new UnsupportedOperationException( "Cette méthode n'est pas sensée servir ici." );
    }

    public List<Ouvrage> getOuvragesFromJson( JSONObject jsOuvrages ) {
        List<Ouvrage> listOuvrage = new ArrayList<Ouvrage>();

        JSONArray arrayOuvrage = jsOuvrages.getJSONArray( "Ouvrage" );

        int nbOuvrage = arrayOuvrage.length();

        for ( int i = 0 ; i < nbOuvrage ; i++ ) {
            try {
                listOuvrage.add( new Ouvrage( arrayOuvrage.getJSONObject( i ) ) );

            } catch ( Exception e ) {
                System.out.println( e.toString() );
            }

        }

//        System.out.println( "fin deserialisation Ouvrage\nNombre de Ouvrage : " + listOuvrage.size() );
        return listOuvrage;
    }

    public List<Ouvrage> getOuvrageFromDB( int index ) throws SQLException {
        List<Ouvrage> vl_ListOuvrages = new LinkedList();

        ShifuDAOFactory.getOeuvreDAO().getByEqualsCustomArgument( new CustomArgument( "1", "1" ) );

        return vl_ListOuvrages;
    }

    @Override
    public List<Ouvrage> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Ouvrage ouvrage ) throws Exception {
        // supression Auteur_oeuvre
        for ( Auteur auteur : ouvrage.getAuteurs() ) {
            ShifuDAOFactory.getAuteurOeuvreDAO().delete( new TableAuteurOeuvre( auteur.getID(), ouvrage.getIdOeuvre() ) );
        }
        // suppression des identifiants
        for ( IdentifiantOeuvre id : ouvrage.getIdentifiants() ) {
            ShifuDAOFactory.getIdentifiantOeuvreDAO().delete( id );
        }
        // suppression Exemplaire
        ShifuDAOFactory.getExemplaireDAO().delete( new TableExemplaire( ouvrage.getIdExemplaire(), null, null, 0, 0 ) );
        // suppression Livre
        ShifuDAOFactory.getLivreDAO().delete( new TableLivre( null, null, null, null, 0, ouvrage.getIdOeuvre() ) );

        //supression Oeuvre
        ShifuDAOFactory.getOeuvreDAO().delete( new TableOeuvre( ouvrage ) );

    }

}
