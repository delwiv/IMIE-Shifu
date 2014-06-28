/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.elementaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.IdentifiantOeuvre;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author delwiv
 */
public class IdentifiantOeuvreDAO extends DAO<IdentifiantOeuvre> {

    @Override
    public IdentifiantOeuvre create( IdentifiantOeuvre object ) {
        try {

            preparedStatement = getPreparedStatement( "INSERT INTO Identifiant_oeuvre (lib_identifiant_oeuvre, valeur_identifiant_oeuvre, ID_oeuvre) VALUES (?, ?, ?);" );
            preparedStatement.setString( 1, object.getLib() );
            preparedStatement.setString( 2, object.getValue() );
            preparedStatement.setInt( 3, object.getIdOeuvre() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                object = getByID( resultSet.getInt( 1 ) );

            } else {
                throw new SQLException( "Probleme db : aucune clé générée." );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();

        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch ( SQLException ex ) {
            }
        }
        return object;
    }

    @Override
    public IdentifiantOeuvre getByID( int ID ) {
        IdentifiantOeuvre identifiantOeuvre = null;
        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Identifiant_oeuvre WHERE ID_identifiant_oeuvre = " + ID );
            if ( resultSet.first() ) {
                identifiantOeuvre = new IdentifiantOeuvre(
                        resultSet.getInt( "ID_identifiant_oeuvre" ),
                        resultSet.getString( "lib_identifiant_oeuvre" ),
                        resultSet.getString( "valeur_identifiant_oeuvre" ),
                        resultSet.getInt( "ID_oeuvre" )
                );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return identifiantOeuvre;
    }

    @Override
    public IdentifiantOeuvre update( IdentifiantOeuvre object ) {
        try {
            preparedStatement = connection.prepareStatement( "UPDATE Identifiant_oeuvre set "
                    + "lib_identifiant_oeuvre=?,"
                    + "valeur_identifiant_oeuvre=?,"
                    + "ID_oeuvre=?"
                    + "WHERE ID_identifiant_oeuvre=?" );

            preparedStatement.setString( 1, object.getLib() );
            preparedStatement.setString( 2, object.getValue() );
            preparedStatement.setInt( 3, object.getIdOeuvre() );
            preparedStatement.setInt( 4, object.getId() );

            preparedStatement.executeQuery();

            object = getByID( object.getId() );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( IdentifiantOeuvreDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public List<IdentifiantOeuvre> getByEqualsCustomArgument( CustomArgument argument ) {
        List<IdentifiantOeuvre> listIdentifiantOeuvre = null;
        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Identifiant_oeuvre WHERE ? = ?" );
            listIdentifiantOeuvre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );

        }
        return listIdentifiantOeuvre;

    }

    @Override
    public List<IdentifiantOeuvre> getByLikeCustomArgument( CustomArgument argument ) {
        List<IdentifiantOeuvre> listIdentifiantOeuvre = null;
        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Identifiant_oeuvre WHERE ? like  %?%" );
            listIdentifiantOeuvre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );

        }
        return listIdentifiantOeuvre;

    }

    @Override
    protected List<IdentifiantOeuvre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<IdentifiantOeuvre> listIdentifiantOeuvre = new ArrayList();
        try {
            preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 2, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 2, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
                listIdentifiantOeuvre.add( new IdentifiantOeuvre(
                        resultSet.getInt( "ID_identifiant_oeuvre" ),
                        resultSet.getString( "lib_identifiant_oeuvre" ),
                        resultSet.getString( "valeur_identifiant_oeuvre" ),
                        resultSet.getInt( "ID_oeuvre" ) ) );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return listIdentifiantOeuvre;
    }

    @Override
    public List<IdentifiantOeuvre> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( IdentifiantOeuvre object ) throws SQLException {
        getPreparedStatement( "DELETE FROM Identifiant_oeuvre WHERE ID_identifiant_oeuvre = " + object.getId() ).executeUpdate();
    }

}
