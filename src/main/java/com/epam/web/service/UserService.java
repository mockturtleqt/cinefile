package com.epam.web.service;

import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dao.exception.DAOException;
import com.epam.web.domain.User;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ValidationException;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.dbConnection.ConnectionPool;
import com.epam.web.util.dbConnection.ProxyConnection;
import com.epam.web.util.resource.MessageManager;
import com.epam.web.util.validation.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class UserService extends AbstractService<User> {
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";
    private static final String UPDATE_USER_RATING_ERROR_MSG = "msg.update.user.rating.error";

    private static final Logger logger = LogManager.getLogger();
    private UserValidation userValidation = new UserValidation();

    public User create(User user) throws ServiceException, InterruptedException, ValidationException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User currentUser = new User();
            if (userValidation.isValid(user)) {
                currentUser = userDAO.create(user);
            } else {
                currentUser.setValidationExceptions(userValidation.getValidationExceptions());
            }
            return currentUser;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
        }
    }

    public User findById(int id) throws ServiceException, InterruptedException, NoSuchPageException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            User user = Optional.ofNullable(userDAO.findById(id))
                    .orElseThrow(() -> new NoSuchPageException(MessageManager.getProperty(FIND_USER_ERROR_MSG)));

            ReviewDAO reviewDAO = new ReviewDAO(connection);
            user.setReviews(reviewDAO.findByUserId(id));

            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            user.setRatings(movieRatingDAO.findByUserId(id));
            return user;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
    }

    public User update(User user) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User currentUser = new User();
            if (userValidation.isValid(user)) {
                currentUser = userDAO.update(user);
            } else {
                currentUser.setValidationExceptions(userValidation.getValidationExceptions());
            }
            return currentUser;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            return userDAO.deleteById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
        }
    }

    public User findByLoginAndPassword(String login, String password) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            return userDAO.findByLoginAndPassword(login, password);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
    }

//    private boolean isValid(User user) throws ValidationException {
//        if (userValidation.isValid(user)) {
//            return true;
//        } else {
//            throw new ValidationException(MessageManager.getProperty(CREATE_USER_ERROR_MSG));
//        }
//    }
}
