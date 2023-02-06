package de.xiekang.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author XZ
 */
public class DBConnection {
    private DBConfiguration DBConfiguration;
    private Connection connection;

    public DBConnection(String TYPE, String SERVER, String PORT, String USER, String PASSWORD) {
        this.DBConfiguration = new DBConfiguration(TYPE, SERVER, PORT, USER, PASSWORD);
        System.out.println("Starting connecting database.....");
        try {
            this.connection = DriverManager.getConnection(DBConfiguration.getJdbcURL(),
                    DBConfiguration.getUser(),
                    DBConfiguration.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DBClose() {
        try {
            this.connection.close();
            System.out.println("Closing database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
