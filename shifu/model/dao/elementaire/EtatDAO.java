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
import shifu.model.EtatOuvrage;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class EtatDAO extends DAO<EtatOuvrage> {

    @Override
    public EtatOuvrage create( EtatOuvrage object ) {

        try {
            List<EtatOuvrage> listEtatOuvrage = getByEqualsCustomArgument( new CustomArgument( "lib_etat", object.getEtatOuvrage() ) );
            if ( listEtatOuvrage.size() == 0 ) {

                preparedStatement = getPreparedStatement( "INSERT INTO Etat (commentaire_etat, lib_etat, pretable_etat ) "
                        + "VALUES (?, ?, ?);" );

                preparedStatement.setString( 1, object.getCommentaire() );
                preparedStatement.setString( 2, object.getEtatOuvrage() );
                preparedStatement.setBoolean( 3, object.isLendable() );

                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();

                if ( resultSet.first() ) {
                    object.setIdEtatOuvrage( resultSet.getInt( 1 ) );
                }
            } else {
                object.setIdEtatOuvrage( listEtatOuvrage.get( 0 ).getIdEtatOuvrage() );
            }

        } catch ( SQLException ex ) {
            Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return object;
    }

    @Override
    public EtatOuvrage getByID( int ID )  {
        EtatOuvrage etat = null;

        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Etat WHERE ID_etat = " + ID );

            if ( resultSet.first() ) {
                etat = new EtatOuvrage(
                        resultSet.getInt( "ID_etat" ),
                        resultSet.getString( "commentaire_etat" ),
                        resultSet.getString( "lib_etat" ),
                        resultSet.getBoolean( "pretable_etat" ) );
            }

        } catch ( SQLException ex ) {
            Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return etat;
    }

    @Override
    public EtatOuvrage update( EtatOuvrage object ) {

        try {
            preparedStatement = connection.prepareStatement( "UPDATE Etat  set "
                    + "commentaire_etat=?,"
                    + "lib_etat=?,"
                    + "pretable_etat=?,"
                    + "WHERE ID_etat=?" );

            preparedStatement.setString( 1, object.getCommentaire() );
            preparedStatement.setString( 2, object.getEtatOuvrage() );
            preparedStatement.setBoolean( 3, object.isLendable() );
            preparedStatement.setInt( 4, object.getIdEtatOuvrage() );
        } catch ( SQLException ex ) {
            Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return object;
    }

    @Override
    public List<EtatOuvrage> getByEqualsCustomArgument( CustomArgument argument ) {
        List<EtatOuvrage> listEtatOuvrage = new ArrayList();

        try {
            preparedStatement = getPreparedStatement( "SELECT * FROM Etat WHERE "
                    + argument.getFieldName() + " = ?" );
            listEtatOuvrage = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listEtatOuvrage;
    }

    @Override
    public List<EtatOuvrage> getByLikeCustomArgument( CustomArgument argument ) {
        List<EtatOuvrage> listEtatOuvrage = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Etat WHERE "
                    + argument.getFieldName() + " like ?" );
            listEtatOuvrage = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( EtatDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listEtatOuvrage;
    }

    @Override
    protected List<EtatOuvrage> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<EtatOuvrage> listEtatOuvrage = new ArrayList();

        try {
//            preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() )//public EtatOuvrage(int idEtatOuvrage, String commentaire, String etatOuvrage, boolean isLendable)
            {
                listEtatOuvrage.add( new EtatOuvrage(
                        resultSet.getInt( "ID_etat" ),
                        resultSet.getString( "commentaire_etat" ),
                        resultSet.getString( "lib_etat" ),
                        resultSet.getBoolean( "pretable_etat" ) ) );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
        }

        return listEtatOuvrage;
    }

    @Override
    public List<EtatOuvrage> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( EtatOuvrage object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
