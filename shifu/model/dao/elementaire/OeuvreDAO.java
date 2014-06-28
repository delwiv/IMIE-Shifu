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
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.tables.TableOeuvre;

/**
 * @author delwiv
 */
public class OeuvreDAO extends DAO<TableOeuvre> {

    @Override
    public TableOeuvre create( TableOeuvre object ) {
        try {

            preparedStatement = getPreparedStatement( "INSERT INTO Oeuvre ("
                    + "titre_oeuvre,"
                    + "titre_original_oeuvre,"
                    + "note_oeuvre,"
                    + "date_publication_oeuvre,"
                    + "couverture_oeuvre,"
                    + "sous_titre_oeuvre,"
                    + "langue_oeuvre,"
                    + "ID_Nationalite,"
                    + "ID_genre)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);" );
            preparedStatement.setString( 1, object.getTitre() );
            preparedStatement.setString( 2, object.getTitreOriginal() );
            preparedStatement.setInt( 3, object.getNote() );
            preparedStatement.setDate( 4, object.getDatePublication() );
            preparedStatement.setString( 5, String.valueOf( object.getIdCouverture() ) );
            preparedStatement.setString( 6, object.getSousTitre() );
            preparedStatement.setString( 7, object.getLangue() );
            preparedStatement.setInt( 8, object.getIdNationalitePaysOrigine() );
            preparedStatement.setInt( 9, object.getIdGenre() );

            System.out.println( preparedStatement.toString() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                object.setIdOeuvre( resultSet.getInt( 1 ) );

            } else {
                throw new SQLException( "Probleme db : aucune clé générée." );
            }

        } catch ( SQLException e ) {
//            System.out.println( object.toString() );
            e.printStackTrace();

        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch ( Exception ex ) {
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public TableOeuvre getByID( int ID ) {
        TableOeuvre tableOeuvre = null;
        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Oeuvre WHERE ID_oeuvre = " + ID );
            if ( resultSet.first() ) {
                tableOeuvre = new TableOeuvre(
                        resultSet.getInt( "ID_oeuvre" ),
                        resultSet.getString( "titre_oeuvre" ),
                        resultSet.getString( "titre_original_oeuvre" ),
                        resultSet.getInt( "note_oeuvre" ),
                        resultSet.getDate( "date_publication_oeuvre" ),
                        resultSet.getInt( "couverture_oeuvre" ),
                        resultSet.getString( "sous_titre_oeuvre" ),
                        resultSet.getString( "langue_oeuvre" ),
                        resultSet.getInt( "ID_Nationalite" ),
                        resultSet.getInt( "ID_genre" ) );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return tableOeuvre;
    }

    @Override
    public TableOeuvre update( TableOeuvre object ) {
        try {
            preparedStatement = getPreparedStatement( "UPDATE Oeuvre set "
                    + "titre_oeuvre = ?,"
                    + "titre_original_oeuvre = ?,"
                    + "note_oeuvre = ?,"
                    + "date_publication_oeuvre = ?,"
                    + "couverture_oeuvre = ?,"
                    + "sous_titre_oeuvre = ?,"
                    + "langue_oeuvre = ?,"
                    + "ID_Nationalite = ? ,"
                    + "ID_genre=?"
                    + "WHERE ID_oeuvre = ?" );
            preparedStatement.setString( 1, object.getTitre() );
            preparedStatement.setString( 2, object.getTitreOriginal() );
            preparedStatement.setInt( 3, object.getNote() );
            preparedStatement.setDate( 4, object.getDatePublication() );
            preparedStatement.setInt( 5, object.getIdCouverture() );
            preparedStatement.setString( 6, object.getSousTitre() );
            preparedStatement.setString( 7, object.getLangue() );
            preparedStatement.setInt( 8, object.getIdNationalitePaysOrigine() );
            preparedStatement.setInt( 9, object.getIdGenre() );
            preparedStatement.setInt( 10, object.getIdOeuvre() );

            preparedStatement.executeUpdate();

            object = getByID( object.getIdOeuvre() );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }
        return object;
    }

    @Override
    public List<TableOeuvre> getByEqualsCustomArgument( CustomArgument argument ) {
        List<TableOeuvre> listTableOeuvre = null;

        try {

            preparedStatement = connection.prepareStatement( "SELECT * FROM Oeuvre WHERE " + argument.getFieldName() + " = ?" );

            listTableOeuvre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class
                    .getName() ).log( Level.SEVERE, null, ex );
        }
        return listTableOeuvre;
    }

    @Override
    public List<TableOeuvre> getByLikeCustomArgument( CustomArgument argument ) {
        List<TableOeuvre> listTableOeuvre = null;

        try {

            preparedStatement = connection.prepareStatement( "SELECT * FROM Oeuvre WHERE " + argument.getFieldName() + " like ?" );

            listTableOeuvre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class
                    .getName() ).log( Level.SEVERE, null, ex );
        }
        return listTableOeuvre;
    }

    @Override
    protected List<TableOeuvre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {

        List<TableOeuvre> listTableOeuvre = new ArrayList();
        try {
            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( int ) value );
            }
//            System.out.println( "Requete SQL : \n" + preparedStatement.toString() );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
                listTableOeuvre.add( new TableOeuvre(
                        resultSet.getInt( "ID_oeuvre" ),
                        resultSet.getString( "titre_oeuvre" ),
                        resultSet.getString( "titre_original_oeuvre" ),
                        resultSet.getInt( "note_oeuvre" ),
                        resultSet.getDate( "date_publication_oeuvre" ),
                        resultSet.getInt( "couverture_oeuvre" ),
                        resultSet.getString( "sous_titre_oeuvre" ),
                        resultSet.getString( "langue_oeuvre" ),
                        resultSet.getInt( "ID_Nationalite" ),
                        resultSet.getInt( "ID_genre" ) ) );
            }
        } catch ( SQLException e ) {
//            e.printStackTrace();
            throw e;
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( Exception e ) {
            }

        }
        return listTableOeuvre;
    }

    @Override
    public List<TableOeuvre> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( TableOeuvre object ) throws SQLException {
        getPreparedStatement( "DELETE FROM Oeuvre WHERE ID_oeuvre = " + object.getIdOeuvre() ).executeUpdate();
    }

}
