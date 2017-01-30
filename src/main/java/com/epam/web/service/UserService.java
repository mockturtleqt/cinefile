package com.epam.web.service;

import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.User;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.resource.MessageManager;
import com.epam.web.validation.UserValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService extends AbstractService<User> {
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";
    private static final String UPDATE_USER_RATING_ERROR_MSG = "msg.update.user.rating.error";

    private static final Logger logger = LogManager.getLogger();

    public boolean create(User user) throws ServiceException, InterruptedException, ValidationException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            UserValidation userValidation = new UserValidation();
            if (userValidation.isValid(user)) {
                success = userDAO.create(user);
            } else {
                throw new ValidationException(MessageManager.getProperty(CREATE_USER_ERROR_MSG));
            }
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public User findById(int id) throws ServiceException, InterruptedException {
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
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }

    public boolean update(User user) throws ServiceException, InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            success = userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            success = userDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public User findByLoginAndPassword(String login, String password) throws ServiceException, InterruptedException {
        User user = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.findByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }
}
