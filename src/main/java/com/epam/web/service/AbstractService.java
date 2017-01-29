package com.epam.web.service;

import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Entity;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ValidationException;

import java.sql.Connection;

public abstract class AbstractService<T extends Entity> {

    public ProxyConnection getConnection() throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        return connectionPool.getConnection();
    }

    public void returnConnection(ProxyConnection proxyConnection) throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.returnConnection(proxyConnection);
    }

    public abstract boolean create(T entity) throws InterruptedException, ValidationException;

    public abstract T findById(int id) throws InterruptedException, NoSuchPageException;

    public abstract boolean update(T entity) throws InterruptedException;

    public abstract boolean deleteById(int id) throws InterruptedException;
}
