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
import shifu.model.Statut;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class StatutDAO extends DAO<Statut>{

    @Override
    public Statut create(Statut object) {
        
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Statut (lib_statut) VALUES (?)");
            
            preparedStatement.setString(1, object.getLib_statut());
            

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.first())
            {
                object = getByID(resultSet.getInt("ID_statut"));
            }
            else
            {
                throw new SQLException("Probleme db : aucune clé générée pour la table Statut");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return object;
    }

    @Override
    public Statut getByID(int ID) {
        Statut statut = null;
        
        try {
            resultSet = this.connection.createStatement().executeQuery("SELECT * FROM Statut WHERE ID_statut = " + ID);
            
            if(resultSet.first())
            {
                statut =new Statut(resultSet.getInt("ID_statut"),
                                   resultSet.getString("lib_statut"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return statut;
    }

    @Override
    public Statut update(Statut object) {
        
        try {
            preparedStatement = connection.prepareStatement("UPDATE Statut set"
                    + "lib_statut=?");
            
            preparedStatement.setString(1, object.getLib_statut());
            
            preparedStatement.executeUpdate();
            
            object = getByID(object.getID());
            
        } catch (SQLException ex) {
            Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return object;
    }

    @Override
    public List<Statut> getByEqualsCustomArgument(CustomArgument argument) {
        List<Statut> listStatut = null;
        
        try {
            preparedStatement = this.connection.prepareStatement("SELECT * FROM Statut WHERE ? = ?");
            
            listStatut = executeCustomSearch(preparedStatement, argument);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listStatut;
    }

    @Override
    public List<Statut> getByLikeCustomArgument(CustomArgument argument) {
        List<Statut> listStatut = null;
        
        try {
            preparedStatement = this.connection.prepareStatement("SELECT * FROM Statut WHERE ? like %?%");
            
            listStatut = executeCustomSearch(preparedStatement, argument);
        } catch (SQLException ex) {
            Logger.getLogger(StatutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listStatut;
    }

    @Override
    protected List<Statut> executeCustomSearch(PreparedStatement statement, CustomArgument argument) throws SQLException {
        List<Statut> listStatut = null;
        
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
            
            preparedStatement.executeQuery();
            
            while(resultSet.next())
            {
                listStatut.add(new Statut(resultSet.getInt("ID_statut"),
                                            resultSet.getString("lib_statut")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            resultSet.close();
            preparedStatement.close();
        }
        
        
        return listStatut;
    }

    @Override
    public List<Statut> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Statut object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
