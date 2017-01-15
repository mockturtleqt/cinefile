package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.entity.type.RoleType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.web.dbConnection.SQLQueries.SQL_INSERT_USER;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_USER;

public class UserDAO extends AbstractDAO<User> {
    private static final String ROLE = "role";
    private static final Logger logger = LogManager.getLogger();

    public UserDAO(Connection connection) {
        super(connection);
    }

    public User findUser(String login, String password) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.getPreparedStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoleType role = RoleType.valueOf(resultSet.getString(ROLE).toUpperCase());
                user = new User(login, password);
                user.setRole(role);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            this.close(preparedStatement);
        }
        return user;
    }

    public boolean addUser(User user) {
        boolean isExecuted = false;
        try {
            PreparedStatement preparedStatement = this.getPreparedStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.sha256Hex(user.getPassword())); //hashing password
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
            isExecuted = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return isExecuted;
    }
}
