package com.epam.web.command;

import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.requestContent.SessionRequestContent;

import java.sql.Connection;

public interface ActionCommand {

    String execute(SessionRequestContent requestContent);

    default public Connection getConnection() throws InterruptedException{
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        return connectionPool.getConnection();
    }

    default public void closeConnection(Connection connection) throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.closeConnection(connection);
    }
}
