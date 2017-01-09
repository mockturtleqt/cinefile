package com.epam.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String dbUrl = resource.getString("db.url");
        String user = resource.getString("db.user");
        String password = resource.getString("db.password");
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(dbUrl, user, password);
    }
}
