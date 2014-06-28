/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import shifu.model.database.MySQLConnection;

/**
 *
 * @author delwiv
 */
public abstract class DAO<T> {

    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    protected static Connection connection = MySQLConnection.getInstance();

    public DAO() {

    }

    protected PreparedStatement getPreparedStatement( String query ) throws SQLException {
        return connection.prepareStatement( query, PreparedStatement.RETURN_GENERATED_KEYS );

    }

    public abstract T create( T object ) throws SQLException;

    public abstract T getByID( int ID ) throws SQLException;

    public abstract T update( T object ) throws SQLException;

    public abstract List<T> getByEqualsCustomArgument( CustomArgument argument ) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract void delete( T object ) throws Exception;

    /**
     * A NE PAS utiliser avec un CustomArgument Int car on utilise le LIKE de
     * SQL qui est fait pour les String
     *
     * @param argument
     * @return
     * @throws java.sql.SQLException
     */
    public abstract List<T> getByLikeCustomArgument( CustomArgument argument ) throws SQLException;

    protected abstract List<T> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException;

}
