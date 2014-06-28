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
import shifu.model.Adherent;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author delwiv
 */
public class AdherentDAO extends DAO<Adherent> {

    @Override
    public Adherent create( Adherent adherent ) throws SQLException {
        try {

            preparedStatement = getPreparedStatement( "INSERT INTO Adherent (date_adhesion, ID_individu) VALUES (? ,?);" );
            preparedStatement.setDate( 1, adherent.getDateAdhesion() );
            preparedStatement.setInt( 2, adherent.getID() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ) {
                adherent = getByID( resultSet.getInt( 1 ) );
            }

        } catch ( SQLException e ) {
            throw e;
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger(AdherentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return adherent;
    }

    @Override
    public Adherent getByID( int ID ) throws SQLException {
        Adherent abonne = null;

        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Adherent WHERE ID_individu = " + ID );

            if ( resultSet.first() ) {
                abonne = new Adherent( resultSet.getDate( "date_adhesion" ) );
            }

        } catch ( SQLException e ) {
            throw e;
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( AdherentDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }

        return abonne;
    }

    @Override
    public Adherent update( Adherent object ) throws SQLException {

        try {
            preparedStatement = connection.prepareStatement( "UPDATE Adherent set "
                    + "date_adhesion=?"
                    + "WHERE ID_individu=?" );

            preparedStatement.setDate( 1, object.getDateAdhesion() );
            preparedStatement.setInt( 2, object.getIdIndividu() );

            preparedStatement.executeUpdate();

        } catch ( SQLException ex ) {
            throw ex;
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( AdherentDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return object;
    }

    @Override
    public List<Adherent> getByEqualsCustomArgument( CustomArgument argument ) throws SQLException {
        List<Adherent> listAbonne = new ArrayList();

        preparedStatement = connection.prepareStatement( "SELECT * FROM Adherent WHERE ? =  ?" );

        listAbonne = executeCustomSearch( preparedStatement, argument );

        return listAbonne;
    }

    @Override
    public List<Adherent> getByLikeCustomArgument( CustomArgument argument ) throws SQLException {
        List<Adherent> listAbonne = new ArrayList();

        preparedStatement = connection.prepareStatement( "SELECT * FROM Adherent WHERE ? like  %?%" );

        listAbonne = executeCustomSearch( preparedStatement, argument );

        return listAbonne;
    }

    @Override
    protected List<Adherent> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Adherent> listAbonne = new ArrayList();

        preparedStatement.setString( 1, argument.getFieldName() );

        Object value = argument.getValue();

        if ( value instanceof String ) {
            preparedStatement.setString( 2, ( String ) value );
        } else if ( value instanceof Integer ) {
            preparedStatement.setInt( 2, ( int ) value );
        }

        resultSet = preparedStatement.executeQuery();

        while ( resultSet.next() ) {
            listAbonne.add( new Adherent( resultSet.getDate( "date_adhesion" ),
                    resultSet.getInt( "ID_individu" ) ) );
        }

        try {
            resultSet.close();
            preparedStatement.close();
        } catch ( Exception e ) {

        }

        return listAbonne;
    }

    @Override
    public List<Adherent> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Adherent object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
