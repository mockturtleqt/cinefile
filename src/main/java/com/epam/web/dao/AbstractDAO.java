package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Entity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    private static final Logger logger = LogManager.getLogger();
    protected ProxyConnection connection;

    public AbstractDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public abstract boolean create(T entity);

    public abstract T findById(int id);

    public abstract boolean deleteById(int id);
//
//    public abstract boolean delete(int id);
//
//    public abstract boolean delete(T entity);
//
//    public abstract boolean create(T entity);
//
//    public abstract T update(T entity);
}
