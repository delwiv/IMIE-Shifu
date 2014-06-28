/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model.dao.elementaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.PointRetrait;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class PointRetraitDAO extends DAO<PointRetrait> {

    @Override
    public PointRetrait create(PointRetrait object) {
        
                        
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Point_retrait (lib_point_retrait, fourchette_dispo_point_retrait, ID_adresse) VALUES (? ,?,?);");
            
            preparedStatement.setString( 1, object.getLibPointRetrait() );
            preparedStatement.setString( 2, object.getFourchetteDispoPointRetrait() );
            preparedStatement.setInt(3, object.getAdresse().getID_adresse());
            
            preparedStatement.executeUpdate();
            
            resultSet = preparedStatement.getGeneratedKeys();
            
            if(resultSet.first())
            {
                object = getByID(resultSet.getInt("ID_point_retrait"));
            }
            else
            {
                throw new SQLException( "Probleme db : aucune clé générée." );
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return object;
    }

    @Override
    public PointRetrait getByID(int ID) {
        
            PointRetrait pointRetrait = null;
            
        try {
            resultSet = this.connection.createStatement().executeQuery("SELECT * FROM Point_Retrait WHERE ID_point_retrait = " + ID);
            
            if(resultSet.first())
            {
                pointRetrait = new PointRetrait(
                        resultSet.getInt("ID_point_retrait"),
                        resultSet.getString("lib_point_retrait"),
                        resultSet.getString("fourchette_dispo_point_retrait"),
                        resultSet.getInt("ID_adresse"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            
        return pointRetrait;
    }

    @Override
    public PointRetrait update(PointRetrait object) {
        
        try {
            preparedStatement = connection.prepareStatement("UPDATE PointRetrait set"
                    + "lib_point_retrait=?"
                    + "fourchette_dispo_point_retrait=?"
                    + "WHERE ID_point_retrait=?");
            
            preparedStatement.setString(1, object.getLibPointRetrait());
            preparedStatement.setString(2, object.getFourchetteDispoPointRetrait());
            preparedStatement.setInt(3, object.getID());
            
            preparedStatement.executeUpdate();
            
            object = getByID(object.getID());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return object;
    }

    @Override
    public List<PointRetrait> getByEqualsCustomArgument(CustomArgument argument) {
        
        List<PointRetrait> listPointRetrait = null;
        
        try {

            preparedStatement = connection.prepareStatement( "SELECT * FROM Point_retrait WHERE ? = ?" );
            listPointRetrait = executeCustomSearch(preparedStatement, argument);
            
        } catch (SQLException ex) {
            Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPointRetrait;
    }

    @Override
    public List<PointRetrait> getByLikeCustomArgument(CustomArgument argument) {
        List<PointRetrait> listPointRetrait = null;
        
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Point_retrait WHERE ? like  %?%");
            listPointRetrait = executeCustomSearch(preparedStatement, argument);
        } catch (SQLException ex) {
            Logger.getLogger(PointRetraitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listPointRetrait;
    }

    @Override
    protected List<PointRetrait> executeCustomSearch( PreparedStatement statement, CustomArgument argument )throws SQLException {
        List<PointRetrait> listPointRetrait = null;
        
        try {
            preparedStatement.setString(1, argument.getFieldName());
            
            Object value = argument.getValue();
            
            if(value instanceof String)
            {
                preparedStatement.setString(2, (String) value);
            }
            else if(value instanceof Integer)
            {
                preparedStatement.setInt(2, (int) value);
            }
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
            {
                listPointRetrait.add(new PointRetrait(
                                        resultSet.getInt("ID_point_retrait"),
                                        resultSet.getString("lib_point_retrait"),
                                        resultSet.getString("fourchette_dispo_point_retrait"),
                                        resultSet.getInt("ID_adresse")));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            preparedStatement.close();
            resultSet.close();
        }
        
        return listPointRetrait;
    }

    @Override
    public List<PointRetrait> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( PointRetrait object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
