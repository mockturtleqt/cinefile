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
        MovieRating movieRating = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MOVIE_RATING_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movieRating = createRatingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        }
        return movieRating;
    }

    public MovieRating create(MovieRating movieRating) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MOVIE_RATING)) {
            preparedStatement.setInt(1, movieRating.getUserId());
            preparedStatement.setInt(2, movieRating.getMovieId());
            preparedStatement.setFloat(3, movieRating.getRate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(CREATE_MOVIE_RATING_ERROR_MSG), e);
        }
        return movieRating;
    }

    public MovieRating update(MovieRating movieRating) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MOVIE_RATING)) {
            preparedStatement.setFloat(1, movieRating.getRate());
            preparedStatement.setInt(2, movieRating.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_MOVIE_RATING_ERROR_MSG), e);
        }
        return movieRating;
    }

    public boolean deleteById(int id) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_MOVIE_RATING_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(DELETE_MOVIE_RATING_ERROR_MSG), e);
        }
    }

    public List<MovieRating> findByUserId(int id) throws DAOException {
        List<MovieRating> ratings = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RATINGS_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MovieRating rating = createRatingFromResultSet(resultSet);
                rating.setMovieTitle(resultSet.getString(MOVIE_TITLE));
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        }
        return ratings;
    }

    public List<MovieRating> findByMovieId(int id) throws DAOException {
        List<MovieRating> ratings = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RATINGS_BY_MOVIE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MovieRating rating = createRatingFromResultSet(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
        }
        return ratings;
    }

    public MovieRating findByUserIdAndMovieId(int userId, int movieId) throws DAOException {
        MovieRating rating = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MOVIE_RATING_BY_USER_ID_AND_MOVIE_ID)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rating = createRatingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MOVIE_RATING_ERROR_MSG), e);
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
