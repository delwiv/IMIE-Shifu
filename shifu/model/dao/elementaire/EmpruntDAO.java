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
import shifu.model.Emprunt;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author antoine
 */
public class EmpruntDAO extends DAO<Emprunt> {

    @Override
    public Emprunt create( Emprunt object ) {

        try {
            preparedStatement = getPreparedStatement( "INSERT INTO Emprunt (date_emprunt, ID_exemplaire, ID_individu) VALUES (?, ?, ?);" );

            preparedStatement.setDate( 1, object.getDateEmprunt() );
            preparedStatement.setInt( 2, object.getOuvrage().getIdExemplaire() );
            preparedStatement.setInt( 3, object.getAbonne().getID() );
            System.out.println( "EmpruntDAO : " + preparedStatement.toString() );
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ) {
                object = getByID( resultSet.getInt( 1 ) );
            } else {
                throw new SQLException( "Probleme db : aucune clé générée." );
            }

        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException ex ) {
//                Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }

        return object;
    }

    @Override
    public Emprunt getByID( int ID ) {
        Emprunt emprunt = null;

        try {
            resultSet = connection.createStatement().executeQuery( "SELECT * FROM Emprunt WHERE ID_emprunt = " + ID );

            if ( resultSet.first() ) {
                emprunt = new Emprunt(
                        resultSet.getInt( "ID_emprunt" ),
                        resultSet.getDate( "date_emprunt" ),
                        resultSet.getInt( "ID_exemplaire" ),
                        resultSet.getInt( "ID_individu" ) );
            }

        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }

        return emprunt;
    }

    @Override
    public Emprunt update( Emprunt object ) {

        try {
            preparedStatement = connection.prepareStatement( "UPDATE Emprunt  set "
                    + "date_emprunt=?,"
                    + "WHERE ID_auteur=?" );

            preparedStatement.setDate( 1, object.getDateEmprunt() );
            preparedStatement.setInt( 2, object.getIdEmprunt() );

            preparedStatement.executeUpdate();

            object = getByID( object.getIdEmprunt() );

        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return object;
    }

    @Override
    public List<Emprunt> getByEqualsCustomArgument( CustomArgument argument ) {
        List<Emprunt> listEmprunt = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Emprunt WHERE "
                    + argument.getFieldName() + " = ? " );

            listEmprunt = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listEmprunt;
    }

    @Override
    public List<Emprunt> getByLikeCustomArgument( CustomArgument argument ) {
        List<Emprunt> listEmprunt = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Emprunt WHERE "
                    + argument.getFieldName() + " LIKE  ?" );

            listEmprunt = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listEmprunt;
    }

    @Override
    protected List<Emprunt> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Emprunt> listEmprunt = new ArrayList();

        Object value = argument.getValue();

        if ( value instanceof String ) {
            preparedStatement.setString( 1, ( String ) value );
        } else if ( value instanceof Integer ) {
            preparedStatement.setInt( 1, ( int ) value );
        }

        resultSet = preparedStatement.executeQuery();

        while ( resultSet.next() ) {//public Emprunt(int idEmprunt, Date dateEmprunt, int idExemplaire, int idIndividu
            listEmprunt.add( new Emprunt(
                    resultSet.getInt( "ID_emprunt" ),
                    resultSet.getDate( "date_emprunt" ),
                    resultSet.getInt( "ID_exemplaire" ),
                    resultSet.getInt( "ID_individu" ) ) );
        }
        return listEmprunt;

    }

    @Override
    public List<Emprunt> findAll() {
        List<Emprunt> lEmprunts = new ArrayList<>();

        try {
            preparedStatement = getPreparedStatement( "SELECT * FROM Emprunt" );
            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {//public Emprunt(int idEmprunt, Date dateEmprunt, int idExemplaire, int idIndividu
                Emprunt emprunt = new Emprunt(
                        resultSet.getInt( "ID_emprunt" ),
                        resultSet.getDate( "date_emprunt" ),
                        resultSet.getInt( "ID_exemplaire" ),
                        resultSet.getInt( "ID_individu" ) );
                emprunt.setAbonne( ShifuDAOFactory.getAdherentDAOC().getByID( emprunt.getIdIndividu() ) );
                emprunt.setOuvrage( ShifuDAOFactory.getOuvrageDAOC().getByID(
                        ShifuDAOFactory.getExemplaireDAO().getByID( emprunt.getIdExemplaire() ).getIdOeuvre()
                ) );
                lEmprunts.add( emprunt );

            }

        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return lEmprunts;
    }

    @Override
    public void delete( Emprunt object ) {
        try {
            preparedStatement = getPreparedStatement( "DELETE FROM Emprunt WHERE ID_emprunt =" + object.getIdEmprunt() );
            preparedStatement.executeUpdate();
            System.out.println( "Deleted : " + object.toString() );

        } catch ( SQLException ex ) {
            Logger.getLogger( EmpruntDAO.class
                    .getName() ).log( Level.SEVERE, null, ex );
        }
    }
}
