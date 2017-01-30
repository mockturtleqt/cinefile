package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.exception.DAOException;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.query.SQLMovieRatingQuery.*;

public class MovieRatingDAO extends AbstractDAO<MovieRating> {
    private static final String ID = "id";
    private static final String RATE = "rate";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String CREATION_DATE = "creation_date";
    private static final String LOGIN = "login";
    private static final String USER_ID = "user_id";
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_TITLE = "movieTitle";
    private static final String CREATE_MOVIE_RATING_ERROR_MSG = "msg.create.movie.rating.error";
    private static final String FIND_MOVIE_RATING_ERROR_MSG = "msg.find.movie.rating.error";
    private static final String DELETE_MOVIE_RATING_ERROR_MSG = "msg.delete.movie.rating.error";
    private static final String UPDATE_MOVIE_RATING_ERROR_MSG = "msg.update.movie.rating.error";

    private static final Logger logger = LogManager.getLogger();

    public MovieRatingDAO(ProxyConnection connection) {
        super(connection);
    }

    public MovieRating findById(int id) throws DAOException {
        PreparedStatement preparedStatement = null;
        MovieRating movieRating = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIE_RATING_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movieRating = this.createRatingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return movieRating;
    }

    public boolean create(MovieRating movieRating) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_INSERT_MOVIE_RATING);
            preparedStatement.setInt(1, movieRating.getUserId());
            preparedStatement.setInt(2, movieRating.getMovieId());
            preparedStatement.setFloat(3, movieRating.getRate());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(CREATE_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public boolean update(MovieRating movieRating) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_MOVIE_RATING);
            preparedStatement.setFloat(1, movieRating.getRate());
            preparedStatement.setInt(2, movieRating.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public boolean deleteById(int id) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_DELETE_MOVIE_RATING_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(DELETE_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public List<MovieRating> findByUserId(int id) throws DAOException {
        PreparedStatement preparedStatement = null;
        List<MovieRating> ratings = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_RATINGS_BY_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MovieRating rating = this.createRatingFromResultSet(resultSet);
                rating.setMovieTitle(resultSet.getString(MOVIE_TITLE));
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return ratings;
    }

    public List<MovieRating> findByMovieId(int id) throws DAOException {
        PreparedStatement preparedStatement = null;
        List<MovieRating> ratings = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_RATINGS_BY_MOVIE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MovieRating rating = this.createRatingFromResultSet(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return ratings;
    }

    public MovieRating findByUserIdAndMovieId(int userId, int movieId) throws DAOException {
        PreparedStatement preparedStatement = null;
        MovieRating rating = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIE_RATING_BY_USER_ID_AND_MOVIE_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rating = this.createRatingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return rating;
    }

    private MovieRating createRatingFromResultSet(ResultSet resultSet) throws SQLException {
        MovieRating rating = new MovieRating();
        rating.setId(resultSet.getInt(ID));
        rating.setRate(resultSet.getFloat(RATE));
        rating.setUserId(resultSet.getInt(USER_ID));
        rating.setMovieId(resultSet.getInt(MOVIE_ID));
        return rating;
    }
}
