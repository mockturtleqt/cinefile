package com.epam.web.dbConnection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String DATABASE = "database";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final Logger logger = LogManager.getLogger();
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(10);

    private ConnectionPool() {
        initializeConnections();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void closeConnection(Connection connection) throws InterruptedException {
        connections.put(connection);
    }

    private void initializeConnections() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DATABASE);
            String dbUrl = resource.getString(DB_URL);
            String user = resource.getString(DB_USER);
            String password = resource.getString(DB_PASSWORD);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            for (int i = 0; i < 10; i++) {
                connections.put(this.getConnection(dbUrl, user, password));
            }
        } catch (SQLException | InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
    }

    private Connection getConnection(String dbUrl, String user, String password) {
        try {
            return DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            logger.log(Level.FATAL, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        try {
            for (Connection connection: connections) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
