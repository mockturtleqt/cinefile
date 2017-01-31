package com.epam.web.service;

import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.Review;
import com.epam.web.entity.User;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.resource.MessageManager;
import com.epam.web.trigger.MovieRatingTrigger;
import com.epam.web.validation.MovieRatingValidation;
import com.sun.xml.internal.bind.v2.TODO;

import java.sql.SQLException;

public class MovieRatingService extends AbstractService<MovieRating> {
    private static final String CREATE_MOVIE_RATING_ERROR_MSG = "msg.create.movie.rating.error";
    private static final String FIND_MOVIE_RATING_ERROR_MSG = "msg.find.movie.rating.error";
    private static final String DELETE_MOVIE_RATING_ERROR_MSG = "msg.delete.movie.rating.error";
    private static final String UPDATE_MOVIE_RATING_ERROR_MSG = "msg.update.movie.rating.error";
    private MovieRatingValidation movieRatingValidation = new MovieRatingValidation();

    public MovieRating create(MovieRating movieRating) throws ServiceException, InterruptedException, ValidationException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()){
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            MovieRating rating = null;
            if (isValid(movieRating)) {
                rating = movieRatingDAO.create(movieRating);
                updateUserRating(connection, movieRating);
            }
            return rating;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MOVIE_RATING_ERROR_MSG), e);
        }
    }

    public MovieRating findById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            return movieRatingDAO.findById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        }
    }

    public MovieRating update(MovieRating movieRating) throws ServiceException, InterruptedException, ValidationException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            MovieRating rating = null;
            if (isValid(movieRating)) {
                rating  = movieRatingDAO.update(movieRating);
                updateUserRating(connection, movieRating);
            }
            return rating;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            return movieRatingDAO.deleteById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MOVIE_RATING_ERROR_MSG), e);
        }
    }

    private void updateUserRating(ProxyConnection connection, MovieRating movieRating) throws DAOException {
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.findById(movieRating.getUserId());

        MovieRatingTrigger movieRatingTrigger = new MovieRatingTrigger();
        int newUserRating = movieRatingTrigger.calculateNewUserRating(user.getUserRating(),
                calculateMovieRatingDifference(connection, movieRating));

        userDAO.updateUserRating(user.getId(), newUserRating);
    }

    private float calculateMovieRatingDifference(ProxyConnection connection, MovieRating movieRating) throws DAOException {
        MovieDAO movieDAO = new MovieDAO(connection);
        Movie movie = movieDAO.findById(movieRating.getMovieId());
        return Math.abs(movie.getRating() - movieRating.getRate());
    }

    private boolean isValid(MovieRating movieRating) throws ValidationException {
        if (movieRatingValidation.isValid(movieRating)) {
            return true;
        } else {
            throw new ValidationException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG));
        }

    }
}
