package com.epam.web.service;

import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService extends AbstractService<User> {
    private static final Logger logger = LogManager.getLogger();

    public User findById(int id) throws InterruptedException {
        User user = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.findById(id);

            ReviewDAO reviewDAO = new ReviewDAO(connection);
            user.setReviews(reviewDAO.findByUserId(id));

            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            user.setRatings(movieRatingDAO.findByUserId(id));
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }

    public User findByLoginAndPassword(String login, String password) throws InterruptedException {
        User user = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.findByLoginAndPassword(login, password);
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }

    public boolean add(User user) throws InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            success = userDAO.add(user);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }
}
