package com.grant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Isura Amarasinghe
 */
public class DBConnManager {

    String sourceURL = null;

    public DBConnManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sourceURL = new String("jdbc:mysql://localhost:3306/grant_db");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println(classNotFoundException + "-----------Unable to load database driver classes");
        }
    }

    public Connection connect() {
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(sourceURL, "root", "root");
            System.out.println("-----------DB connection Success");
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------DB connection failure");
        }
        return dbConn;
    }

    public void con_close(Connection dbConn) {
        try {
            dbConn.close();
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------DB connection closing failure");
        }
    }
}
