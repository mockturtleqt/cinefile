package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.query.SQLReviewQuery.*;

public class ReviewDAO extends AbstractDAO<Review> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String CREATION_DATE = "creation_date";
    private static final String LOGIN = "login";
    private static final String USER_ID = "user_id";
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_TITLE = "movieTitle";
    private static final Logger logger = LogManager.getLogger();

    public ReviewDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean create(Review review) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_INSERT_REVIEW);
            preparedStatement.setString(1, review.getTitle());
            preparedStatement.setString(2, review.getBody());
            preparedStatement.setInt(3, review.getUserId());
            preparedStatement.setInt(4, review.getMovieId());
            preparedStatement.setDate(5, safeLocalDateToSqlDate(review.getDate()));
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public Review findById(int id) {
        PreparedStatement preparedStatement = null;
        Review review = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_REVIEW_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                review = this.createReviewFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return review;
    }

    public boolean update(Review review) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_REVIEW);
            preparedStatement.setString(1, review.getTitle());
            preparedStatement.setString(2, review.getBody());
            preparedStatement.setDate(3, safeLocalDateToSqlDate(review.getDate()));
            preparedStatement.setInt(4, review.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public boolean deleteById(int id) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_DELETE_REVIEW_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public List<Review> findByUserId(int id) {
        PreparedStatement preparedStatement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_REVIEWS_BY_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = this.createReviewFromResultSet(resultSet);
                review.setMovieTitle(resultSet.getString(MOVIE_TITLE));
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return reviews;
    }

    public List<Review> findByMovieId(int id) {
        PreparedStatement preparedStatement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_REVIEWS_BY_MOVIE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = this.createReviewFromResultSet(resultSet);
                review.setUserLogin(resultSet.getString(LOGIN));
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return reviews;
    }

    private Review createReviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt(ID));
        review.setTitle(resultSet.getString(TITLE));
        review.setBody(resultSet.getString(BODY));
        Date creationDate = resultSet.getDate(CREATION_DATE);
        if (creationDate != null) {
            review.setDate(creationDate.toLocalDate());
        }
        review.setUserId(resultSet.getInt(USER_ID));
        review.setMovieId(resultSet.getInt(MOVIE_ID));
        return review;
    }
}
