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
import shifu.model.Nationalite;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author delwiv
 */
public class NationaliteDAO extends DAO<Nationalite> {

    @Override
    public Nationalite create( Nationalite object ) {
        try {
            // recherche en base d'une ligne égale
            List<Nationalite> nationalites = getByLikeCustomArgument( new CustomArgument( "lib_Nationalite", object.getLib() ) );

            if ( nationalites.isEmpty() ) {
                preparedStatement = this.connection
                        .prepareStatement( "INSERT INTO Nationalite (lib_Nationalite, lib_court_Nationalite) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS );

                preparedStatement.setString( 1, object.getLib() );
                preparedStatement.setString( 2, object.getShortLib() );

                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();
                if ( resultSet.next() ) {
                    object = getByID( resultSet.getInt( 1 ) );

                } else {
                    throw new SQLException( "Probleme db : aucune clé générée." );
                }
            } else {
                System.out.println( "Nationalite existe deja en base, création inutile" );
                object = nationalites.get( 0 );
            }

        } catch ( SQLException e ) {
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
    public Nationalite getByID( int ID ) {
        Nationalite nationalite = null;
        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Nationalite WHERE ID_Nationalite = " + ID );
            if ( resultSet.first() ) {
                nationalite = new Nationalite(
                        resultSet.getInt( "ID_Nationalite" ),
                        resultSet.getString( "lib_Nationalite" ),
                        resultSet.getString( "lib_court_nationalite" )
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
        return nationalite;
    }

    @Override
    public Nationalite update( Nationalite object ) {
        try {
            preparedStatement = connection.prepareStatement( "UPDATE Nationalite set "
                    + "lib_nationalite=?,"
                    + "lib_court_nationalite=? "
                    + "WHERE ID_Nationalite=?" );
            preparedStatement.setString( 1, object.getLib() );
            preparedStatement.setString( 2, object.getShortLib() );
            preparedStatement.setInt( 3, object.getID() );

            preparedStatement.executeQuery();

            object = getByID( object.getID() );
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
    public List<Nationalite> getByEqualsCustomArgument( CustomArgument argument ) {
        List<Nationalite> listNationalite = null;

        try {

//            preparedStatement = ;
            listNationalite = executeCustomSearch(
                    getPreparedStatement( "SELECT * FROM Nationalite WHERE " + argument.getFieldName() + " = ?" ),
                    argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return listNationalite;
    }

    @Override
    public List<Nationalite> getByLikeCustomArgument( CustomArgument argument ) {
        List<Nationalite> listNationalite = null;

        try {

            listNationalite = executeCustomSearch( getPreparedStatement( "SELECT * FROM Nationalite WHERE " + argument.getFieldName() + " like ?" ), argument );

        } catch ( SQLException ex ) {
            Logger.getLogger( AuteurDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return listNationalite;
    }

    @Override
    protected List<Nationalite> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Nationalite> listNationalite = new ArrayList();
        try {
//            preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                String str = String.valueOf( value );
                statement.setString( 1, ( str ) );
            } else if ( value instanceof Integer ) {
                statement.setInt( 1, ( int ) value );
            }
//            System.out.println( statement. );

            resultSet = statement.executeQuery();

            while ( resultSet.next() ) {
//                System.out.println( "NAtionalité trouvée!!" );
                listNationalite.add( new Nationalite(
                        resultSet.getInt( "ID_Nationalite" ),
                        resultSet.getString( "lib_Nationalite" ),
                        resultSet.getString( "lib_court_Nationalite" ) ) );

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
        return listNationalite;
    }

    @Override
    public List<Nationalite> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Nationalite object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
