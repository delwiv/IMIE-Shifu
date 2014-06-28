/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author delwiv
 */
public class MySQLConnection {

    private static String username = "shifubeta";
    private static String password = "shifubeta";
//    private static String url = "jdbc:mysql://10.0.0.126:3306/shifubeta";
    private static String url = "jdbc:mariadb://127.0.0.1:3306/shifubeta";
//    private static String url = "jdbc:mysql://127.0.0.1:3306/shifubeta";

    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Méthode qui va nous retourner notre instance et la créer si elle n'existe
     * pas...
     *
     * @return
     */
    public static Connection getInstance() {
        if ( connect == null ) {
            try {
                connect = DriverManager.getConnection( url, username, password );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        return connect;
    }

}
