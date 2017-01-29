package com.epam.web.service;

import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.User;
import com.epam.web.exception.ValidationException;
import com.epam.web.validation.UserValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService extends AbstractService<User> {
    private static final Logger logger = LogManager.getLogger();

    public boolean create(User user) throws InterruptedException, ValidationException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            UserValidation userValidation = new UserValidation();
            if (userValidation.isValid(user)) {
                success = userDAO.create(user);
            } else {
                throw new ValidationException();
            }
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public User findById(int id) throws InterruptedException {
        User user = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.findById(id);

            if (user != null) {
                ReviewDAO reviewDAO = new ReviewDAO(connection);
                user.setReviews(reviewDAO.findByUserId(id));

                MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
                user.setRatings(movieRatingDAO.findByUserId(id));
            }
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }

    public boolean update(User user) throws InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            success = userDAO.update(user);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            success = userDAO.deleteById(id);
        } finally {
            super.returnConnection(connection);
        }
        return success;
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
}
