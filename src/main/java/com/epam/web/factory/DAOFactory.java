//package com.epam.web.factory;
//
//import com.epam.web.dao.*;
//import com.epam.web.dbConnection.ConnectionPool;
//import com.epam.web.dbConnection.ProxyConnection;
//import com.epam.web.entity.Entity;
//import com.epam.web.entity.User;
//import com.epam.web.exception.NoSuchDAOClassException;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.sql.Connection;
//
//public class DAOFactory {
//    private final String USER = "User";
//    private final String MOVIE = "Movie";
//    private final String MEDIA_PERSON = "MediaPerson";
//    private final String MOVIE_RATING = "MovieRating";
//    private final String REVIEW = "Review";
//
//    private static final Logger logger = LogManager.getLogger();
//    private ConnectionPool connectionPool = ConnectionPool.getInstance();
//
//    private ProxyConnection connection;
//
//    public DAOFactory() {
//        this.initConnection();
//    }
//
//    public AbstractDAO getDAOClass(String className) throws NoSuchDAOClassException {
//        switch (className) {
//            case (USER):
//                return new UserDAO(connection);
//            case (MOVIE):
//                return new MovieDAO(connection);
//            case (MEDIA_PERSON):
//                return new MediaPersonDAO(connection);
//            case (MOVIE_RATING):
//                return new MovieRatingDAO(connection);
//            case (REVIEW):
//                return new ReviewDAO(connection);
//            default:
//                throw new NoSuchDAOClassException();
//        }
//    }
//
//    private void initConnection() {
//        try {
//            Connection connection = connectionPool.getConnection();
//            this.connection = new ProxyConnection(connection);
//        } catch (InterruptedException e) {
//            logger.log(Level.ERROR, e);
//        }
//    }
//
//    public void returnConnection() {
//        try {
//            connectionPool.returnConnection(this.connection.getConnection());
//        } catch (InterruptedException e) {
//            logger.log(Level.ERROR, e);
//        }
//    }
//}
