package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.User;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.RoleType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.web.dbConnection.SQLQueries.SQL_INSERT_USER;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_USER_BY_ID;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD;

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

    private static final Logger logger = LogManager.getLogger();

    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    public User findById(int id) {
        PreparedStatement preparedStatement = null;
        User user = new User();
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

    public User findByLoginAndPassword(String login, String password) {
        User user = new User();
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

    public boolean add(User user) {
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

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE)));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setBanned(resultSet.getBoolean(IS_BANNED));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setGender(GenderType.valueOf(resultSet.getString(GENDER)));
        user.setBirthday(resultSet.getDate(BIRTHDAY));
        user.setPicture(resultSet.getString(PICTURE));
        return user;
    }

}
