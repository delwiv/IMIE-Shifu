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
import shifu.model.Individu;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class IndividuDAO extends DAO<Individu> {

    @Override
    public Individu create( Individu individu ) throws SQLException {

            preparedStatement = getPreparedStatement( "INSERT INTO Individu (nom_individu, prenom_individu, titre_individu, email_individu, telephone_individu, "
                    + "date_naissance_individu, ID_adresse,ID_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?);" );
            preparedStatement.setString( 1, individu.getNom() );
            preparedStatement.setString( 2, individu.getPrenom() );
            preparedStatement.setString( 3, individu.getTitre() );
            preparedStatement.setString( 4, individu.getEmail() );
            preparedStatement.setString( 5, individu.getTel() );
            preparedStatement.setDate( 6, individu.getDateNaissance() );
            preparedStatement.setInt( 7, individu.getAdresse().getID_adresse() );
            //preparedStatement.setInt(8, individu.getIdLogin());
            preparedStatement.setNull( 8, java.sql.Types.INTEGER );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ) {
                individu = getByID( resultSet.getInt( 1 ) );
            }

            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException ex ) {
            }
        
        return individu;
    }

    @Override
    public List<Individu> getByEqualsCustomArgument( CustomArgument argument ) throws SQLException {
        List<Individu> listIndivdu = new ArrayList();

            preparedStatement = connection.prepareStatement( "SELECT * FROM Individu WHERE ? = ? " );

            listIndivdu = executeCustomSearch( preparedStatement, argument );
      

        return listIndivdu;
    }

    @Override
    public Individu getByID( int ID ) throws SQLException {
        Individu individu = null;
            resultSet = this.connection
                    .createStatement().executeQuery( "SELECT * FROM Individu WHERE ID_individu = " + ID );

            if ( resultSet.first() ) {
                individu = new Individu(
                        resultSet.getInt( "ID_individu" ),
                        resultSet.getString( "nom_individu" ),
                        resultSet.getString( "prenom_individu" ),
                        resultSet.getString( "titre_individu" ),
                        resultSet.getString( "email_individu" ),
                        resultSet.getString( "telephone_individu" ),
                        resultSet.getDate( "date_naissance_individu" ),
                        resultSet.getInt( "ID_adresse" ),
                        resultSet.getInt( "ID_login" ) );
            }

            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                
            }
      

        return individu;
    }

    @Override
    public Individu update( Individu object ) throws SQLException {

            
            object = getByID( object.getID() );
            
            preparedStatement = connection.prepareStatement( "UPDATE Individu  set "
                    + "nom_individu=?, "
                    + "prenom_individu=?, "
                    + "titre_individu=?, "
                    + "email_individu=?, "
                    + "telephone_individu=?, "
                    + "date_naissance_individu=? "
                    + "WHERE ID_individu=?" );

            preparedStatement.setString( 1, object.getNom() );
            preparedStatement.setString( 2, object.getPrenom() );
            preparedStatement.setString( 3, object.getTitre() );
            preparedStatement.setString( 4, object.getEmail() );
            preparedStatement.setString( 5, object.getTel() );
            preparedStatement.setDate( 6, object.getDateNaissance() );
            preparedStatement.setInt( 7, object.getID() );

            preparedStatement.executeUpdate();

            

            try {
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( IndividuDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        
        return object;
    }

    @Override
    public List<Individu> getByLikeCustomArgument( CustomArgument argument ) throws SQLException {
        List<Individu> listIndivdu = new ArrayList();

            preparedStatement = connection.prepareStatement( "SELECT * FROM Individu WHERE ? like %?%" );

            listIndivdu = executeCustomSearch( preparedStatement, argument );


        return listIndivdu;
    }

    @Override
    protected List<Individu> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Individu> listIndividu = new ArrayList();

            preparedStatement.setString( 1, argument.getFieldName() );

            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 2, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 2, ( int ) value );
            }

            resultSet = preparedStatement.executeQuery();
            //Individu(int ID, String nom, String prenom, String titre, String email, String tel, Date dateNaissance, int idAdresse, int idLogin
            while ( resultSet.next() ) {
                listIndividu.add( new Individu(
                        resultSet.getInt( "ID_individu" ),
                        resultSet.getString( "nom_individu" ),
                        resultSet.getString( "prenom_individu" ),
                        resultSet.getString( "titre_individu" ),
                        resultSet.getString( "email_individu" ),
                        resultSet.getString( "telephone_individu" ),
                        resultSet.getDate( "date_naissance_individu" ),
                        resultSet.getInt( "ID_adresse" ),
                        resultSet.getInt( "ID_login" ) ) );
            }
            try {
            resultSet.close();
            preparedStatement.close();
            } catch (Exception ex) {
                
        }

        return listIndividu;
    }
    
    public List<Adherent> getOnlyAdherent(){
        List<Adherent> ladherents = new ArrayList<Adherent>();
        
        try {
            preparedStatement = connection.prepareStatement( "SELECT * FROM Individu, Adherent WHERE Individu.ID_individu = Adherent.ID_individu" );
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ladherents.add( new Adherent
                        (new Individu(
                                resultSet.getInt( "ID_individu"),
                                resultSet.getString( "nom_individu"),
                                resultSet.getString( "prenom_individu" ),
                                resultSet.getString( "titre_individu" ),
                                resultSet.getString( "email_individu" ),
                                resultSet.getString( "telephone_individu" ),
                                resultSet.getDate( "date_naissance_individu" ),
                                resultSet.getInt( "ID_adresse" ),
                                resultSet.getInt( "ID_login" )),
                        resultSet.getDate( "date_adhesion" )) );
                
                
                
                
            }
            
        } catch ( SQLException ex ) {
            Logger.getLogger( IndividuDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        finally
        {
            
            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( IndividuDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
            
        }
        
        return ladherents;
    }

    @Override
    public List<Individu> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Individu object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
