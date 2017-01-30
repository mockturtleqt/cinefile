package com.epam.web.service;

import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
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

public class MovieRatingService extends AbstractService<MovieRating> {
    private static final String CREATE_MOVIE_RATING_ERROR_MSG = "msg.create.movie.rating.error";
    private static final String FIND_MOVIE_RATING_ERROR_MSG = "msg.find.movie.rating.error";
    private static final String DELETE_MOVIE_RATING_ERROR_MSG = "msg.delete.movie.rating.error";
    private static final String UPDATE_MOVIE_RATING_ERROR_MSG = "msg.update.movie.rating.error";

    public boolean create(MovieRating movieRating) throws ServiceException, InterruptedException, ValidationException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            MovieRatingValidation movieRatingValidation = new MovieRatingValidation();
            if (movieRatingValidation.isValid(movieRating)) {
                movieRatingDAO.create(movieRating);
            } else {
                throw new ValidationException(MessageManager.getProperty(CREATE_MOVIE_RATING_ERROR_MSG));
            }
            updateUserRating(connection, movieRating);
            success = true;
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public MovieRating findById(int id) throws ServiceException, InterruptedException {
        MovieRating movieRating = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movieRating = movieRatingDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return movieRating;
    }

    public boolean update(MovieRating movieRating) throws ServiceException, InterruptedException, ValidationException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            MovieRatingValidation movieRatingValidation = new MovieRatingValidation();
            if (movieRatingValidation.isValid(movieRating)) {
                movieRatingDAO.update(movieRating);
            } else {
                throw new ValidationException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG));
            }
            updateUserRating(connection, movieRating);
            success = true;
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG), e);
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
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            success = movieRatingDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    private boolean updateUserRating(ProxyConnection connection, MovieRating movieRating) throws DAOException {
        boolean success;
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.findById(movieRating.getUserId());

        MovieRatingTrigger movieRatingTrigger = new MovieRatingTrigger();
        int newUserRating = movieRatingTrigger.calculateNewUserRating(user.getUserRating(),
                this.calculateMovieRatingDifference(connection, movieRating));

        success = userDAO.updateUserRating(user.getId(), newUserRating);
        return success;
    }

    private float calculateMovieRatingDifference(ProxyConnection connection, MovieRating movieRating) throws DAOException {
        MovieDAO movieDAO = new MovieDAO(connection);
        Movie movie = movieDAO.findById(movieRating.getMovieId());
        return Math.abs(movie.getRating() - movieRating.getRate());
    }
}
