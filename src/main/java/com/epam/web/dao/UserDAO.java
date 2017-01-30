package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.User;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.RoleType;
import com.epam.web.trigger.MovieRatingTrigger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.epam.web.dbConnection.query.SQLUserQuery.*;

public class UserDAO extends AbstractDAO<User> {
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String IS_BANNED = "is_banned";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String GENDER = "gender";
    private static final String BIRTHDAY = "birthday";
    private static final String PICTURE = "picture";
    private static final String USER_RATING = "user_rating";
    private static final String RATING = "rating";
    private static final String RATE = "rate";

    private static final Logger logger = LogManager.getLogger();

    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean create(User user) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.sha256Hex(user.getPassword())); //hashing password
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public User findById(int id) {
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = this.createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return user;
    }

    public boolean update(User user) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, safeEnumToString(user.getGender()));
            preparedStatement.setDate(5, safeLocalDateToSqlDate(user.getBirthday()));
            preparedStatement.setString(6, user.getPicture());
            preparedStatement.setInt(7, user.getId());

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
            preparedStatement = connection.getPreparedStatement(SQL_DELETE_USER_BY_ID);
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

    public User findByLoginAndPassword(String login, String password) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = this.createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return user;
    }

    public boolean hasRatedThisMovie(int movieId, int userId) {
        boolean hasRated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_USERS_WHO_RATED_THIS_MOVIE);
            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (userId == resultSet.getInt(ID)) {
                    hasRated = true;
                    break;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return hasRated;
    }

    public boolean updateUserRating(int userId, int newUserRating) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_USER_RATING);
            preparedStatement.setInt(1, newUserRating);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

//    public boolean updateUserRating(int movieId) {
//        boolean success = false;
//        PreparedStatement selectPreparedStatement = null;
//        PreparedStatement updatePreparedStatement = null;
//        MovieRatingTrigger trigger = new MovieRatingTrigger();
//        try {
//            selectPreparedStatement = connection.getPreparedStatement(SQL_SELECT_USERS_WHO_RATED_THIS_MOVIE);
//            updatePreparedStatement = connection.getPreparedStatement(SQL_UPDATE_USER_RATING);
//            selectPreparedStatement.setInt(1, movieId);
//            ResultSet resultSet = selectPreparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int userId = resultSet.getInt(ID);
//                int userRating = resultSet.getInt(USER_RATING);
//                float movieRating = resultSet.getFloat(RATING);
//                float rate = resultSet.getFloat(RATE);
//                int newUserRating = trigger.calculateNewUserRating(userRating, Math.abs(movieRating - rate));
//                updatePreparedStatement.setInt(1, newUserRating);
//                updatePreparedStatement.setInt(2, userId);
//                updatePreparedStatement.executeUpdate();
//                success = true;
//            }
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, e);
//        } finally {
//            connection.closeStatement(selectPreparedStatement);
//            connection.closeStatement(updatePreparedStatement);
//        }
//        return success;
//    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE).toUpperCase()));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setIsBanned(resultSet.getBoolean(IS_BANNED));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));

        String genderString = resultSet.getString(GENDER);
        GenderType genderType = (genderString != null) ? GenderType.valueOf(genderString.toUpperCase()) : null;
        user.setGender(genderType);

        Date date = resultSet.getDate(BIRTHDAY);
        LocalDate localDate = (date != null) ? date.toLocalDate() : null;
        user.setBirthday(localDate);

        user.setPicture(resultSet.getString(PICTURE));
        user.setUserRating(resultSet.getInt(USER_RATING));
        return user;
    }

}
