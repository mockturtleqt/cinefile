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
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String dbUrl = resource.getString("db.url");
            String user = resource.getString("db.user");
            String password = resource.getString("db.password");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            for (int i = 0; i < 10; i++) {
                connections.put(DriverManager.getConnection(dbUrl, user, password));
            }
        } catch (SQLException | InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
