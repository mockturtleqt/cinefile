package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.exception.DAOException;
import com.epam.web.domain.Movie;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.dbConnection.ConnectionPool;
import com.epam.web.util.dbConnection.ProxyConnection;
import com.epam.web.util.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MovieService extends AbstractService<Movie> {
    private static final String CREATE_MOVIE_ERROR_MSG = "msg.create.movie.error";
    private static final String FIND_MOVIE_ERROR_MSG = "msg.find.movie.error";
    private static final String DELETE_MOVIE_ERROR_MSG = "msg.delete.movie.error";
    private static final String UPDATE_MOVIE_ERROR_MSG = "msg.update.movie.error";
    private static final Logger logger = LogManager.getLogger();

    public Movie create(Movie movie) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.create(movie);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MOVIE_ERROR_MSG), e);
        }
    }

    public Movie findById(int id) throws ServiceException, InterruptedException, NoSuchPageException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);

            Movie movie = Optional.ofNullable(movieDAO.findById(id))
                    .orElseThrow(() -> new NoSuchPageException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG)));

            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            movie.setCrew(mediaPersonDAO.findByMovieId(id));

            ReviewDAO reviewDAO = new ReviewDAO(connection);
            movie.setReviews(reviewDAO.findByMovieId(id));

            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movie.setRatingList(movieRatingDAO.findByMovieId(id));

            return movie;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        }
    }

    public Movie update(Movie movie) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.update(movie);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MOVIE_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.deleteById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MOVIE_ERROR_MSG), e);
        }
    }

    public List<Movie> findAll() throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.findAll();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        }
    }

    public List<Movie> findByNamePart(String title) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.findByNamePart(title);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        }
    }

    public List<Movie> findTopMovies() throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.findTopMovies();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MOVIE_ERROR_MSG), e);
        }
    }
}
