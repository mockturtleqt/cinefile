package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ServiceException;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieService extends AbstractService<Movie> {
    private static final String CREATE_MOVIE_ERROR_MSG = "msg.create.movie.error";
    private static final String FIND_MOVIE_ERROR_MSG = "msg.find.movie.error";
    private static final String DELETE_MOVIE_ERROR_MSG = "msg.delete.movie.error";
    private static final String UPDATE_MOVIE_ERROR_MSG = "msg.update.movie.error";
    private static final Logger logger = LogManager.getLogger();

    public boolean create(Movie movie) throws ServiceException, InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);
            success = movieDAO.create(movie);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public Movie findById(int id) throws ServiceException, InterruptedException, NoSuchPageException {
        ProxyConnection connection = null;
        Movie movie = null;
        try {
            connection = super.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);

            movie = Optional.ofNullable(movieDAO.findById(id))
                    .orElseThrow(() -> new NoSuchPageException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG)));

            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            movie.setCrew(mediaPersonDAO.findByMovieId(id));

            ReviewDAO reviewDAO = new ReviewDAO(connection);
            movie.setReviews(reviewDAO.findByMovieId(id));

            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movie.setRatingList(movieRatingDAO.findByMovieId(id));
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return movie;
    }

    public boolean update(Movie movie) throws ServiceException, InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);
            success = movieDAO.update(movie);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MOVIE_ERROR_MSG), e);
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
            MovieDAO movieDAO = new MovieDAO(connection);
            success = movieDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public List<Movie> findAll() throws ServiceException, InterruptedException {
        List<Movie> movies = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MovieDAO movieDAO = new MovieDAO(connection);
            movies = movieDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return movies;
    }

    public List<Movie> findByNamePart(String title) throws ServiceException, InterruptedException {
        List<Movie> movies = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MovieDAO movieDAO = new MovieDAO(connection);
            movies = movieDAO.findByNamePart(title);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return movies;
    }

    public List<Movie> findTopMovies() throws ServiceException, InterruptedException {
        List<Movie> movies = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MovieDAO movieDAO = new MovieDAO(connection);
            movies = movieDAO.findTopMovies();
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return movies;
    }
}
