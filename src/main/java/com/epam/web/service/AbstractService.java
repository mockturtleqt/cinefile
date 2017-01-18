package com.epam.web.service;

import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Entity;

import java.sql.Connection;

public abstract class AbstractService<T extends Entity> {

    public ProxyConnection getConnection() throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        return new ProxyConnection(connection);
    }

    public void returnConnection(ProxyConnection proxyConnection) throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = proxyConnection.getConnection();
        connectionPool.returnConnection(connection);
    }

    public abstract boolean add(T entity) throws InterruptedException;
}
