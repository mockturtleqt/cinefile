package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.Review;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_RATINGS_BY_USER_ID;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_REVIEWS_BY_USER_ID;

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
    private static final Logger logger = LogManager.getLogger();

    public MovieRatingDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean add(MovieRating movieRating) {
        boolean success = false;
        return success;
    }

    public List<MovieRating> findByUserId(int id) {
        PreparedStatement preparedStatement = null;
        List<MovieRating> ratings = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_RATINGS_BY_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MovieRating rating = this.createRatingFromResultSet(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return ratings;
    }

    private MovieRating createRatingFromResultSet(ResultSet resultSet) throws SQLException {
        MovieRating rating = new MovieRating();
        rating.setId(resultSet.getInt(ID));
        rating.setRate(resultSet.getFloat(RATE));
        rating.setMovieTitle(resultSet.getString(MOVIE_TITLE));
        rating.setUserId(resultSet.getInt(USER_ID));
        rating.setMovieId(resultSet.getInt(MOVIE_ID));
        return rating;
    }
}
