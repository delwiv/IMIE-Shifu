/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.elementaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.tables.TableLivre;

/**
 *
 * @author delwiv
 */
public class LivreDAO extends DAO<TableLivre> {

    @Override
    public TableLivre create( TableLivre object ) {
        try {
            preparedStatement = getPreparedStatement( "INSERT INTO Livre ("
                    + "collection_livre,"
                    + "format_livre,"
                    + "maison_edition_livre,"
                    + "resume_livre,"
                    + "nb_page_livre,"
                    + "ID_oeuvre) VALUES (?, ?, ?, ?, ?, ?);" );
            preparedStatement.setString( 1, object.getCollection() );
            preparedStatement.setString( 2, object.getFormat() );
            preparedStatement.setString( 3, object.getMaisonEdition() );
            preparedStatement.setString( 4, object.getResume() );
            preparedStatement.setInt( 5, object.getNbPages() );
            preparedStatement.setInt( 6, object.getIdOeuvre() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ) {
                object = getByID( resultSet.getInt( 1 ) );

            } else {
//                throw new SQLException( "Probleme db : aucune clé générée." );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public TableLivre getByID( int ID ) {
        TableLivre tableLivre = null;
        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Auteur WHERE ID_auteur = " + ID );
            if ( resultSet.first() ) {
                tableLivre = new TableLivre(
                        resultSet.getString( "collection_livre" ),
                        resultSet.getString( "format_livre" ),
                        resultSet.getString( "maison_edition_livre" ),
                        resultSet.getString( "resume_livre" ),
                        resultSet.getInt( "nb_page_livre" ),
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
        return tableLivre;
    }

    @Override
    public TableLivre update( TableLivre object ) {
        try {
            preparedStatement = connection.prepareStatement( "UPDATE Livre  set "
                    + "collection_livre=?,"
                    + "format_livre=?,"
                    + "maison_edition_livre=?,"
                    + "resume_livre=?"
                    + "nb_page_livre=?"
                    + "WHERE ID_oeuvre=?" );
            preparedStatement.setString( 1, object.getCollection() );
            preparedStatement.setString( 2, object.getFormat() );
            preparedStatement.setString( 3, object.getMaisonEdition() );
            preparedStatement.setString( 4, object.getResume() );
            preparedStatement.setInt( 5, object.getNbPages() );
            preparedStatement.setInt( 6, object.getIdOeuvre() );

            preparedStatement.executeUpdate();

            object = getByID( object.getIdOeuvre() );
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
    public List<TableLivre> getByEqualsCustomArgument( CustomArgument argument ) {
        List<TableLivre> tableLivre = null;

        try {

            preparedStatement = connection.prepareStatement( "SELECT * FROM Livre WHERE ? = ?" );

            tableLivre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return tableLivre;
    }

    @Override
    public List<TableLivre> getByLikeCustomArgument( CustomArgument argument ) {
        List<TableLivre> tableLivre = null;

        try {

            preparedStatement = connection.prepareStatement( "SELECT * FROM Livre WHERE ? like %?%" );

            tableLivre = executeCustomSearch( preparedStatement, argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return tableLivre;
    }

    @Override
    protected List<TableLivre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<TableLivre> listTableLivre = null;
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
                listTableLivre.add( new TableLivre(
                        resultSet.getString( "collection_livre" ),
                        resultSet.getString( "format_livre" ),
                        resultSet.getString( "maison_edition_livre" ),
                        resultSet.getString( "resume_livre" ),
                        resultSet.getInt( "nb_page_livre" ),
                        resultSet.getInt( "ID_oeuvre" ) ) );
            }
        } catch ( SQLException e ) {
//            e.printStackTrace();
            throw e;
        } finally {
            resultSet.close();
            preparedStatement.close();
        }
        return listTableLivre;
    }

    @Override
    public List<TableLivre> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( TableLivre object ) throws SQLException {
        getPreparedStatement( "DELETE FROM Livre where ID_oeuvre = " + object.getIdOeuvre() ).executeUpdate();
    }

}
