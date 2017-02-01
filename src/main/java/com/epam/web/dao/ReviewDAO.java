package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;
import com.epam.web.exception.DAOException;
import com.epam.web.resource.MessageManager;
import com.mysql.jdbc.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String CREATE_REVIEW_ERROR_MSG = "msg.create.review.error";
    private static final String FIND_REVIEW_ERROR_MSG = "msg.find.review.error";
    private static final String DELETE_REVIEW_ERROR_MSG = "msg.delete.review.error";
    private static final String UPDATE_REVIEW_ERROR_MSG = "msg.update.review.error";

    private static final Logger logger = LogManager.getLogger();

    public ReviewDAO(ProxyConnection connection) {
        super(connection);
    }

    public Review create(Review review) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, review.getTitle());
            preparedStatement.setString(2, review.getBody());
            preparedStatement.setInt(3, review.getUserId());
            preparedStatement.setInt(4, review.getMovieId());
            preparedStatement.setDate(5, safeLocalDateToSqlDate(review.getDate()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                review.setId(generatedKeys.getInt(1));
            }
            return review;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(CREATE_REVIEW_ERROR_MSG), e);
        }
    }

    public Review findById(int id) throws DAOException {
        Review review = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_REVIEW_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                review = createReviewFromResultSet(resultSet);
            }
            return review;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_REVIEW_ERROR_MSG), e);
        }
    }

    public Review update(Review review) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_REVIEW)) {
            preparedStatement.setString(1, review.getTitle());
            preparedStatement.setString(2, review.getBody());
            preparedStatement.setDate(3, safeLocalDateToSqlDate(review.getDate()));
            preparedStatement.setInt(4, review.getId());
            preparedStatement.executeUpdate();
            return review;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_REVIEW_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_REVIEW_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(DELETE_REVIEW_ERROR_MSG), e);
        }
    }

    public List<Review> findByUserId(int id) throws DAOException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_REVIEWS_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = createReviewFromResultSet(resultSet);
                review.setMovieTitle(resultSet.getString(MOVIE_TITLE));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_REVIEW_ERROR_MSG), e);
        }
    }

    public List<Review> findByMovieId(int id) throws DAOException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_REVIEWS_BY_MOVIE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = createReviewFromResultSet(resultSet);
                review.setUserLogin(resultSet.getString(LOGIN));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_REVIEW_ERROR_MSG), e);
        }
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
