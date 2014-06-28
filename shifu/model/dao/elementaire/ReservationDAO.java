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
import shifu.model.Reservation;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 * Cette classe contient les methodes de lecture et d'ecriture de la base de données pour la table Reservation
 * @author antoine
 */
public class ReservationDAO extends DAO<Reservation>{

    @Override
    public Reservation create(Reservation object) {
        
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Reservation(date_reservation, ID_individu, ID_exemplaire, ID_point_retrait) VALUES (?,?,?,?)");
            
            preparedStatement.setDate(1, object.getDateReservation());
            preparedStatement.setInt(2, object.getIdIndividu());
            preparedStatement.setInt(3, object.getIdExemplaire());
            preparedStatement.setInt(4, object.getIdPointRetrait());
            
            preparedStatement.executeUpdate();
            
            
            resultSet = preparedStatement.getGeneratedKeys();
            
            if(resultSet.first())
            {
                object = getByID(resultSet.getInt("ID_reservation"));
            }
            else
            {
                throw new SQLException("Probleme db : aucune clé générée pour la table Reservation");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        
        return object;
    }

    @Override
    public Reservation getByID(int ID) {
        Reservation reservation = null;
        
        try {
            resultSet = this.connection.createStatement().executeQuery("SELECT * FROM Reservation WHERE ID_reservation = " + ID);
            
            if(resultSet.first())
            {
                reservation = new Reservation(
                                resultSet.getInt("ID_reservation"),
                                resultSet.getDate("date_reservation"),
                                resultSet.getInt("ID_individu"),
                                resultSet.getInt("ID_exemplaire"),
                                resultSet.getInt("ID_point_retrait"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return reservation;
    }

    @Override
    public Reservation update(Reservation object) {
        
        try {
            preparedStatement = this.connection.prepareStatement("UPDATE Reservation set"
                    + "date_reservation=?"
                    + "WHERE ID_reservation=?");
            
            preparedStatement.setDate(1, object.getDateReservation());
            preparedStatement.setInt(2, object.getID());
            
            preparedStatement.executeUpdate();
            
            object = getByID(object.getID());
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return object;
    }

    @Override
    public List<Reservation> getByEqualsCustomArgument(CustomArgument argument) {
        List<Reservation> listReservation = null;
        
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM reservation WHERE ? = ?");
            
            listReservation = executeCustomSearch(preparedStatement, argument);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listReservation;
    }

    @Override
    public List<Reservation> getByLikeCustomArgument(CustomArgument argument) {
        
            List<Reservation> listReservation = null;
        try {    
            preparedStatement = connection.prepareStatement("SELECT * FROM reservation WHERE ? like %?%");
            
            listReservation = executeCustomSearch(preparedStatement, argument);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listReservation;
    }

    @Override
    protected List<Reservation> executeCustomSearch(PreparedStatement statement, CustomArgument argument) throws SQLException {
        List<Reservation> listReservation = null;
        
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
            
            while(resultSet.next())//public Reservation(int ID, Date dateReservation, int idIndividu, int idExemplaire, int idPointRetrait) {
            {
                listReservation.add(new Reservation(resultSet.getInt("ID_reservation"),
                                                    resultSet.getDate("date_reservation"),
                                                    resultSet.getInt("ID_individu"),
                                                    resultSet.getInt("ID_exemplaire"),
                                                    resultSet.getInt("ID_point_retrait")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            resultSet.close();
            preparedStatement.close();
        }
        
        
        
        return listReservation;
    }

    @Override
    public List<Reservation> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Reservation object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
