package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.User;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.RoleType;
import com.epam.web.exception.DAOException;
import com.epam.web.resource.MessageManager;
import org.apache.commons.codec.digest.DigestUtils;
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
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";
    private static final String UPDATE_USER_RATING_ERROR_MSG = "msg.update.user.rating.error";

    private static final Logger logger = LogManager.getLogger();

    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    public User create(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.sha256Hex(user.getPassword())); //hashing password
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
        }
        return user;
    }

    public User findById(int id) throws DAOException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
        return user;
    }

    public User update(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, safeEnumToString(user.getGender()));
            preparedStatement.setDate(5, safeLocalDateToSqlDate(user.getBirthday()));
            preparedStatement.setString(6, user.getPicture());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
        return user;
    }

    public boolean deleteById(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
        }
    }

    public User findByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
        return user;
    }
//
//    public boolean hasRatedThisMovie(int movieId, int userId) throws DAOException {
//        boolean hasRated = false;
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_WHO_RATED_THIS_MOVIE);
//            preparedStatement.setInt(1, movieId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                if (userId == resultSet.getInt(ID)) {
//                    hasRated = true;
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, e);
//        } finally {
//            connection.closeStatement(preparedStatement);
//        }
//        return hasRated;
//    }

    public void updateUserRating(int userId, int newUserRating) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_RATING)) {
            preparedStatement.setInt(1, newUserRating);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_USER_RATING_ERROR_MSG), e);
        }
    }

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
