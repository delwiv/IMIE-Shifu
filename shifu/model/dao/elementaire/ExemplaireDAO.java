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
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.tables.TableExemplaire;

/**
 *
 * @author delwiv
 */
public class ExemplaireDAO extends DAO<TableExemplaire> {

    @Override
    public TableExemplaire create( TableExemplaire object ) {

        try {
            preparedStatement = this.connection
                    .prepareStatement( "INSERT INTO Exemplaire ("
                            + "dispo_exemplaire,"
                            + "commentaire_exemplaire,"
                            + "ID_oeuvre,"
                            + "ID_etat) VALUES (?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1, object.getDispo() );
            preparedStatement.setString( 2, object.getCommentaire() );
            preparedStatement.setInt( 3, object.getIdOeuvre() );
            preparedStatement.setInt( 4, object.getIdEtat() );

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
//                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public TableExemplaire getByID( int ID ) {
        TableExemplaire tableExemplaire = null;
        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Exemplaire WHERE ID_exemplaire = " + ID );
            if ( resultSet.first() ) {
                tableExemplaire = new TableExemplaire(
                        resultSet.getInt( "ID_exemplaire" ),
                        resultSet.getString( "dispo_exemplaire" ),
                        resultSet.getString( "commentaire_exemplaire" ),
                        resultSet.getInt( "ID_oeuvre" ),
                        resultSet.getInt( "ID_etat" )
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
        return tableExemplaire;
    }

    @Override
    public TableExemplaire update( TableExemplaire object ) {
        try {
            preparedStatement = connection.prepareStatement( "UPDATE Exemplaire  set "
                    + "dispo_exemplaire=?,"
                    + "commentaire_exemplaire=?,"
                    + "ID_oeuvre=?,"
                    + "ID_etat=?"
                    + "WHERE ID_exemplaire=?" );

            preparedStatement.setString( 1, object.getDispo() );
            preparedStatement.setString( 2, object.getCommentaire() );
            preparedStatement.setInt( 3, object.getIdOeuvre() );
            preparedStatement.setInt( 4, object.getIdEtat() );
            preparedStatement.setInt( 5, object.getIdExemplaire() );

            preparedStatement.executeUpdate();

            object = getByID( object.getIdExemplaire() );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }
        return object;
    }

    @Override
    public List<TableExemplaire> getByEqualsCustomArgument( CustomArgument argument ) {
        List<TableExemplaire> listTableExemplaire = null;

        try {

            preparedStatement = getPreparedStatement( "SELECT * FROM Exemplaire WHERE "
                    + argument.getFieldName() + " = ?" );

            listTableExemplaire = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return listTableExemplaire;
    }

    @Override
    public List<TableExemplaire> getByLikeCustomArgument( CustomArgument argument ) {
        List<TableExemplaire> listTableExemplaire = null;

        try {

            preparedStatement = getPreparedStatement( "SELECT * FROM Exemplaire WHERE "
                    + argument.getFieldName() + " LIKE %?%" );

            listTableExemplaire = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return listTableExemplaire;
    }

    @Override
    protected List<TableExemplaire> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {

        List<TableExemplaire> listTableExemplaire = new ArrayList();
        try {
            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
                listTableExemplaire.add( new TableExemplaire(
                        resultSet.getInt( "ID_exemplaire" ),
                        resultSet.getString( "dispo_exemplaire" ),
                        resultSet.getString( "commentaire_exemplaire" ),
                        resultSet.getInt( "ID_oeuvre" ),
                        resultSet.getInt( "ID_etat" ) ) );
            }
        } catch ( SQLException e ) {
//            e.printStackTrace();
            throw e;
        } finally {
            resultSet.close();
            preparedStatement.close();
        }
        return listTableExemplaire;
    }

    @Override
    public List<TableExemplaire> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( TableExemplaire object ) throws SQLException {
        try {
        getPreparedStatement( "DELETE FROM Exemplaire WHERE ID_exemplaire = " + object.getIdExemplaire() ).executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
