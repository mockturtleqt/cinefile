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

//    public abstract List<T> findAll(String title);
    //public abstract T find(String ... params);
    public abstract boolean add(T entity);
//    public abstract T findEntityById(int id);
//
//    public abstract boolean delete(int id);
//
//    public abstract boolean delete(T entity);
//
//    public abstract boolean create(T entity);
//
//    public abstract T update(T entity);
//    protected PreparedStatement getPreparedStatement(String SQLQuery) {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(SQLQuery);
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, e);
//        }
//        return preparedStatement;
//    }

//    public void close(Statement statement) {
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, e);
//        }
//    }
}
