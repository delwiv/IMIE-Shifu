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
import shifu.model.Adresse;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class AdresseDAO extends DAO<Adresse> {

    @Override
    public Adresse create( Adresse adresse ) {

        try {
            /*preparedStatement = this.connection
                    .prepareStatement( "INSERT INTO Adresse (ligne1_adresse, ligne2_adresse, ligne3_adresse, CP_adresse, ville_adresse, "
                            + "lien_gmaps_adresse, ID_point_retrait) VALUES (?, ?, ?, ?, ?, ?,?);" );*/
            
            
            
            preparedStatement = this.getPreparedStatement( "INSERT INTO Adresse (ligne1_adresse, ligne2_adresse, ligne3_adresse, CP_adresse, ville_adresse, "
                            + "lien_gmaps_adresse) VALUES (?, ?, ?, ?, ?, ?);");

            preparedStatement.setString( 1, adresse.getLigne1_adresse() );
            preparedStatement.setString( 2, adresse.getLigne2_adresse() );
            preparedStatement.setString( 3, adresse.getLigne3_adresse() );
            preparedStatement.setString( 4, adresse.getCP_adresse() );
            preparedStatement.setString( 5, adresse.getVille_adresse() );
            preparedStatement.setString( 6, adresse.getLien_gmaps_adresse() );
            //preparedStatement.setInt( 7, adresse.getIdPointRetrait() );
            
            
            
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.first() ) {
                                
                adresse = getByID( resultSet.getInt( 1 ) );
            }

        } catch ( SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
//                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException e ) {
//                e.printStackTrace();
            }
        }
        return adresse;
    }

    @Override
    public Adresse getByID( int ID ) {
        Adresse adresse = null;

        try {
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Adresse WHERE ID_adresse = " + ID );

            if ( resultSet.first() ) {
                adresse = new Adresse(
                        resultSet.getInt( "ID_adresse" ),
                        resultSet.getString( "ligne1_adresse" ),
                        resultSet.getString( "ligne2_adresse" ),
                        resultSet.getString( "ligne3_adresse" ),
                        resultSet.getString( "CP_adresse" ),
                        resultSet.getString( "ville_adresse" ),
                        resultSet.getString( "lien_gmaps_adresse" )/*,
                        resultSet.getInt( "ID_point_retrait" )*/ );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( AdresseDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return adresse;
    }

    @Override
    public Adresse update( Adresse object ) {

        try {
            
            object = getByID( object.getID_adresse() );
            
            preparedStatement = connection.prepareStatement( "UPDATE Adresse  set "
                    + "ligne1_adresse=?,"
                    + "ligne2_adresse=?,"
                    + "ligne3_adresse=?,"
                    + "CP_adresse=?,"
                    + "ville_adresse=?,"
                    + "lien_gmaps_adresse=? "
                    + "WHERE ID_adresse=? " );

            preparedStatement.setString( 1, object.getLigne1_adresse() );
            preparedStatement.setString( 2, object.getLigne2_adresse() );
            preparedStatement.setString( 3, object.getLigne3_adresse() );
            preparedStatement.setString( 4, object.getCP_adresse() );
            preparedStatement.setString( 5, object.getVille_adresse() );
            preparedStatement.setNull( 6 , java.sql.Types.INTEGER );
//            preparedStatement.setInt( 7, object.getID_adresse() );
            preparedStatement.setInt( 7, object.getID_adresse() );

            preparedStatement.executeUpdate();

            
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( AdresseDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return object;
    }

    @Override
    public List<Adresse> getByEqualsCustomArgument( CustomArgument argument
    ) {
        List<Adresse> listAdresse = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Adresse WHERE ? = ?" );
            listAdresse = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( AdresseDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listAdresse;
    }

    @Override
    public List<Adresse> getByLikeCustomArgument( CustomArgument argument
    ) {
        List<Adresse> listAdresse = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Adresse WHERE ? like %?%" );
            listAdresse = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( AdresseDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return listAdresse;
    }

    @Override
    protected List<Adresse> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Adresse> listAdresse = new ArrayList();

        try {

            preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 2, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 2, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();
            /*public Adresse(int ID_adresse, String ligne1_adresse, String ligne2_adresse, String ligne3_adresse, 
             String CP_adresse, String ville_adresse, String lien_gmaps_adresse, int idPointRetrait) */
            while ( resultSet.next() ) {
                listAdresse.add( new Adresse(
                        resultSet.getInt( "ID_adresse" ),
                        resultSet.getString( "ligne1_adresse" ),
                        resultSet.getString( "ligne2_adresse" ),
                        resultSet.getString( "ligne3_adresse" ),
                        resultSet.getString( "CP_adresse" ),
                        resultSet.getString( "ville_adresse" ),
                        resultSet.getString( "lien_gmaps_adresse" ) ) );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return listAdresse;
    }

    @Override
    public List<Adresse> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Adresse object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
