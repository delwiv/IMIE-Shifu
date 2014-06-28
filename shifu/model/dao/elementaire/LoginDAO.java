/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model.dao.elementaire;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.Login;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author antoine
 */
public class LoginDAO extends DAO <Login>{

    @Override
    public Login create(Login login) {
        
        
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Login (login, password, ID_individu) VALUES (?,?,?)");
            
            preparedStatement.setString(1, login.getLogin());
            preparedStatement.setBytes(2, login.getPassword());
            preparedStatement.setInt(3, login.getID_individu());
            
            preparedStatement.executeUpdate();
            
            resultSet = preparedStatement.getGeneratedKeys();
            
            if(resultSet.first())
            {
                login = getByID(resultSet.getInt("ID_login"));
            }
            else
            {
                throw new SQLException("Probleme db : aucune clé générée pour la table Login");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return login;
    }

    @Override
    public Login getByID(int ID) {
        Login login = null;
        
        try {
            resultSet = this.connection.createStatement().executeQuery("SELECT * FROM Login WHERE ID_login = " + ID);
                                    
            if(resultSet.first())
            {
                login = new Login(//public Login(int ID, String login, byte[] password, int ID_individu)
                                resultSet.getInt("ID_login"),
                                resultSet.getString("login"),
                                resultSet.getBytes("password"),
                                resultSet.getInt("ID_individu"));
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return login;
    }

    @Override
    public Login update(Login object) {
        
        
        try {
            preparedStatement = this.connection.prepareStatement("UPDATE Login set"
                    + "login=?"
                    + "password=?"
                    + "WHERE ID_login=?");
            
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setBytes(2, object.getPassword());
            preparedStatement.setInt(3, object.getID());
            
            preparedStatement.executeUpdate();
            
            object = getByID(object.getID());
                        
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return object;
    }

    @Override
    public List<Login> getByEqualsCustomArgument(CustomArgument argument) {
        List<Login> listLogin = null;
        
        try {
            preparedStatement = this.connection.prepareStatement("SELECT * FROM Login WHERE ? = ?");
            
            listLogin = executeCustomSearch(preparedStatement, argument);
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listLogin;
    }

    @Override
    public List<Login> getByLikeCustomArgument(CustomArgument argument) {
        List<Login> listLogin = null;
        
        try {
            preparedStatement = this.connection.prepareStatement("SELECT * FROM Login WHERE ? like %?%");
            
            
            listLogin = executeCustomSearch(preparedStatement, argument);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listLogin;
    }

    @Override
    protected List<Login> executeCustomSearch(PreparedStatement statement, CustomArgument argument) throws SQLException {
        List<Login> listLogin = null;
        
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
            
            while(resultSet.next())//public Login(int ID, String login, byte[] password, int ID_individu)
            {
                listLogin.add(new Login(resultSet.getInt("ID_login"),
                                                    resultSet.getString("login"),
                                                    resultSet.getBytes("password"),
                                                    resultSet.getInt("ID_individu")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            resultSet.close();
            preparedStatement.close();
        }
        
        return listLogin;
    }

    @Override
    public List<Login> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Login object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
