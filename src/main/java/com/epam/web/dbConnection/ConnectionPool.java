package com.epam.web.dbConnection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    private static final int POOL_SIZE = 10;
    private static final Lock lock = new ReentrantLock();
    private static ConnectionPool instance;

    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(POOL_SIZE);
    private DatabaseInitializer databaseInitializer;

    private ConnectionPool() {
        databaseInitializer = new DatabaseInitializer();
        this.initializeConnections();
    }

    public static ConnectionPool getInstance() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void returnConnection(Connection connection) throws InterruptedException {
        connections.put(connection);
    }

    private void initializeConnections() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                connections.put(databaseInitializer.getConnection());
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        try {
            for (Connection connection : connections) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
