/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.elementaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.Auteur;
import shifu.model.Nationalite;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author delwiv
 */
public class AuteurDAO extends DAO<Auteur> {

    static private Nationalite nationaliteInconnu;
    static private Auteur auteurInconnu;

    public Nationalite getNationaliteInconnu() throws SQLException {
        if ( null == nationaliteInconnu ) {
            nationaliteInconnu = ShifuDAOFactory.getNationaliteDAO()
                    .getByEqualsCustomArgument( new CustomArgument( "lib_Nationalite", "Inconnu" ) )
                    .get( 0 );  // la méthode retourne une list on ne prend que la premiere (et unique) occurence
        }
        return nationaliteInconnu;
    }

    public Auteur getAuteurInconnu() throws SQLException {
        if ( null == auteurInconnu ) {
            auteurInconnu = getByEqualsCustomArgument( new CustomArgument( "nom_auteur", "Inconnu" ) ).get( 0 );
        }
        return auteurInconnu;
    }

    @Override
    public Auteur create( Auteur object ) throws SQLException {

        // pour vérifier si l'auteur est à créer ou non
        boolean createInDB = true;
        if ( null == object.getNationalite() ) {
            object.setNationnalite( getNationaliteInconnu() );
        }

        preparedStatement = getPreparedStatement( "SELECT ID_auteur, nom_auteur, prenom_auteur, surnom_auteur, commentaire_auteur, ID_Nationalite "
                + "FROM Auteur where nom_auteur = ? "
                + "and prenom_auteur = ? "
                + "and surnom_auteur = ? "
                + "and id_Nationalite = ?;" );

        preparedStatement.setString( 1, object.getNom() );
        preparedStatement.setString( 2, object.getPrenom() );
        preparedStatement.setString( 3, object.getSurnom() );
        preparedStatement.setInt( 4, object.getNationalite().getID() );

        resultSet = preparedStatement.executeQuery();

//            System.out.println( resultSet.toString() );
        while ( resultSet.next() ) {
            int id = resultSet.getInt( "ID_auteur" );
            String nom = resultSet.getString( "nom_auteur" );
            String prenom = resultSet.getString( "prenom_auteur" );
            String surnom = resultSet.getString( "surnom_auteur" );
            String comment = resultSet.getString( "commentaire_auteur" );

            Auteur auteur = new Auteur( id, nom, prenom, surnom, "", null );

            auteur.setNationnalite( ShifuDAOFactory.getNationaliteDAO().getByID( resultSet.getInt( "ID_Nationalite" ) ) );

            if ( object.equals( auteur ) ) {
                createInDB = false;
                object = auteur;
                System.out.println( "L'auteur " + object + " existe déjà en base!" );
            }
        }

        if ( createInDB ) {

            preparedStatement = getPreparedStatement( "INSERT INTO Auteur (nom_auteur, prenom_auteur, surnom_auteur, commentaire_auteur, ID_Nationalite) VALUES (?, ?, ?, ?, ?);" );
            preparedStatement.setString( 1, object.getNom() );
            preparedStatement.setString( 2, object.getPrenom() );
            preparedStatement.setString( 3, object.getSurnom() );
            preparedStatement.setString( 4, object.getCommentaire_auteur() );
            if ( !( object.getNationalite().getID() > 0 ) ) {
                object.setNationnalite( ShifuDAOFactory.getNationaliteDAO().create( object.getNationalite() ) );
            }

            preparedStatement.setInt( 5, object.getNationalite().getID() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                //                Auteur newAuteur = getByID( resultSet.getInt( 1 ) );
//                // on reaffecte les observers
//                newAuteur.setObservers( object.getObservers() );
//                object = newAuteur;
                try {

                    object.setID( resultSet.getInt( 1 ) );
                } catch ( Exception ex ) {
                }

            } else {
                throw new SQLException( "Probleme db : aucune clé générée." );
            }
        }

        try {
            preparedStatement.close();
            resultSet.close();
        } catch ( Exception ex ) {
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return object;
    }

    @Override
    public Auteur getByID( int ID ) throws SQLException {
        Auteur auteur = null;
        resultSet = this.connection
                .createStatement().executeQuery( "SELECT * FROM Auteur WHERE ID_auteur = " + ID );
        if ( resultSet.first() ) {
            auteur = new Auteur(
                    resultSet.getInt( "ID_auteur" ),
                    resultSet.getString( "nom_auteur" ),
                    resultSet.getString( "prenom_auteur" ),
                    resultSet.getString( "surnom_auteur" ),
                    resultSet.getString( "commentaire_auteur" )
            );
            int idNationalite = resultSet.getInt( "ID_Nationalite" );
            auteur.setNationnalite( ShifuDAOFactory.getNationaliteDAO().getByID( idNationalite ) );
        }

        try {
            resultSet.close();
        } catch ( SQLException ex ) {
        }

        return auteur;

    }

    @Override
    public Auteur update( Auteur object ) throws SQLException {
        preparedStatement = connection.prepareStatement( "UPDATE Auteur  set "
                + "nom_auteur=?,"
                + "prenom_auteur=?,"
                + "surnom_auteur=?,"
                + "commentaire_auteur=?"
                + "WHERE ID_auteur=?" );
        preparedStatement.setString( 1, object.getNom() );
        preparedStatement.setString( 2, object.getPrenom() );
        preparedStatement.setString( 3, object.getSurnom() );
        preparedStatement.setString( 4, object.getCommentaire_auteur() );
        preparedStatement.setInt( 5, object.getID() );

        preparedStatement.executeUpdate();

        object = getByID( object.getID() );

        try {
            preparedStatement.close();
        } catch ( SQLException ex ) {

        }

        return object;
    }

    @Override
    public List<Auteur> getByLikeCustomArgument( CustomArgument argument ) throws SQLException {
        List<Auteur> listAuteurs = null;

        preparedStatement = connection.prepareStatement( "SELECT * FROM Auteur WHERE " + argument.getFieldName() + " like  ?" );

        listAuteurs = executeCustomSearch( preparedStatement, argument );

        return listAuteurs;

    }

    @Override
    public List<Auteur> getByEqualsCustomArgument( CustomArgument argument ) throws SQLException {
        List<Auteur> listAuteurs = null;

        preparedStatement = connection.prepareStatement( "SELECT * FROM Auteur WHERE " + argument.getFieldName() + " =  ?" );

        listAuteurs = executeCustomSearch( preparedStatement, argument );

        return listAuteurs;
    }

    @Override
    protected List<Auteur> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {

        List<Auteur> listAuteurs = new ArrayList();

//            preparedStatement.setString( 1, argument.getFieldName() );
        Object value = argument.getValue();

        if ( value instanceof String ) {
            preparedStatement.setString( 1, ( String ) value );
        } else if ( value instanceof Integer ) {
            preparedStatement.setInt( 1, ( int ) value );
        }

        resultSet = preparedStatement.executeQuery();

        while ( resultSet.next() ) {
            Auteur auteur = new Auteur(
                    resultSet.getInt( "ID_auteur" ),
                    resultSet.getString( "nom_auteur" ),
                    resultSet.getString( "prenom_auteur" ),
                    resultSet.getString( "surnom_auteur" ),
                    resultSet.getString( "commentaire_auteur" ) );
            try {
                auteur.setNationnalite( ShifuDAOFactory.getNationaliteDAO().getByID( resultSet.getInt( "ID_Nationalite" ) ) );
            } catch ( Exception ex ) {
                auteur.setNationnalite( new Nationalite( "Inconnu" ) );
            }
            listAuteurs.add( auteur );

        }

//            e.printStackTrace();
        try {
            resultSet.close();
            preparedStatement.close();
        } catch ( Exception e ) {
        }

        return listAuteurs;
    }

    @Override
    public List<Auteur> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Auteur object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
