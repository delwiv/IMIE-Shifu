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
import shifu.model.dao.tables.TableStatutIndividu;

/**
 *
 * @author antoine
 */
public class StatutIndividuDAO extends DAO<TableStatutIndividu>{

    @Override
    public TableStatutIndividu create(TableStatutIndividu object) {
        
        try {
            preparedStatement = this.connection
                    .prepareStatement( "INSERT INTO Statut_individu (ID_individu, ID_statut) VALUES (?, ?);" );
            
            preparedStatement.setInt(1, object.getIdIndividu());
            preparedStatement.setInt(2, object.getIdStatut());
            
            preparedStatement.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StatutIndividuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatutIndividuDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return object;
    }

    @Override
    public TableStatutIndividu getByID(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TableStatutIndividu update(TableStatutIndividu object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TableStatutIndividu> getByEqualsCustomArgument(CustomArgument argument) {
        
        List<TableStatutIndividu> listTableStatuIndividu = new ArrayList();
        try {
            
            
            preparedStatement = connection.prepareStatement( "SELECT * FROM Statut_individu WHERE ? = ?" );
            listTableStatuIndividu = executeCustomSearch( preparedStatement, argument );
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StatutIndividuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTableStatuIndividu;
    }

    @Override
    public List<TableStatutIndividu> getByLikeCustomArgument(CustomArgument argument) {
        throw new UnsupportedOperationException( "On ne peut faire de recherche à base de String ici." );
    }

    @Override
    protected List<TableStatutIndividu> executeCustomSearch(PreparedStatement statement, CustomArgument argument) throws SQLException {
        
        List<TableStatutIndividu> listTableStatuIndividu = new ArrayList();
        
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
                listTableStatuIndividu.add( new TableStatutIndividu(
                        resultSet.getInt( "ID_individu" ),
                        resultSet.getInt( "ID_statut" ) ) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }finally {
            resultSet.close();
            preparedStatement.close();
        }
        
        
        return listTableStatuIndividu;
    }

    @Override
    public List<TableStatutIndividu> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( TableStatutIndividu object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
