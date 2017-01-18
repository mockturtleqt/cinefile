package com.epam.web.dbConnection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProxyConnection {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement(String SQLQuery) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQuery);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return preparedStatement;
    }

    public void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
