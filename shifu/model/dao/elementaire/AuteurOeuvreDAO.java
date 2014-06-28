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
import shifu.model.dao.tables.TableAuteurOeuvre;

/**
 *
 * @author delwiv
 */
public class AuteurOeuvreDAO extends DAO<TableAuteurOeuvre> {

    @Override
    public TableAuteurOeuvre create( TableAuteurOeuvre object ) {
        try {
            preparedStatement = getPreparedStatement( "INSERT INTO Auteur_oeuvre (ID_auteur, ID_oeuvre) VALUES (?, ?);" );
//            if ( object.getIdAuteur() == 0 ) {
//                // si pas d'auteur on met à inconnu
//                object.setIdAuteur( 1 );
//            }
            preparedStatement.setInt( 1, object.getIdAuteur() );
            preparedStatement.setInt( 2, object.getIdOeuvre() );

            System.out.println( preparedStatement.toString() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
//                resultSet.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public TableAuteurOeuvre getByID( int ID ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TableAuteurOeuvre update( TableAuteurOeuvre object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TableAuteurOeuvre> getByEqualsCustomArgument( CustomArgument argument ) {
        List<TableAuteurOeuvre> listAuteurOeuvre = null;

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Auteur_oeuvre WHERE " + argument.getFieldName() + " = ?" );
            listAuteurOeuvre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return listAuteurOeuvre;
    }

    @Override
    public List<TableAuteurOeuvre> getByLikeCustomArgument( CustomArgument argument ) {
        throw new UnsupportedOperationException( "On ne peut faire de recherche à base de String ici." );
    }

    @Override
    protected List<TableAuteurOeuvre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<TableAuteurOeuvre> listAuteurOeuvre = new ArrayList();
        try {
            //preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
                int idAuteur = resultSet.getInt( "ID_auteur" );
                int idOeuvre = resultSet.getInt( "ID_oeuvre" );
                listAuteurOeuvre.add( new TableAuteurOeuvre( idAuteur, idOeuvre ) );

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
        return listAuteurOeuvre;
    }

    @Override
    public List<TableAuteurOeuvre> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( TableAuteurOeuvre object ) throws SQLException {
        getPreparedStatement( "DELETE FROM Auteur_oeuvre WHERE ID_oeuvre = " + object.getIdOeuvre() ).executeUpdate();
    }

}
