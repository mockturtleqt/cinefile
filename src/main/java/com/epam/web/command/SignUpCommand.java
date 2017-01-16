package com.epam.web.command;

import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.entity.User;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Map;

public class SignUpCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL_PARAM = "email";
    private static final String FIRST_NAME_PARAM = "first-name";
    private static final String LAST_NAME_PARAM = "last-name";
    private final static Logger logger = LogManager.getLogger();
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            User user = createUser(requestContent);
            userDAO.addUser(user);
            requestContent.setSessionAttribute(USER_ATTR, user.getLogin());
            page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
            connectionPool.closeConnection(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    private User createUser(SessionRequestContent requestContent) {
        String login = requestContent.getParameter(LOGIN_PARAM);
        String password = requestContent.getParameter(PASSWORD_PARAM);
        String email = requestContent.getParameter(EMAIL_PARAM);
        String firstName = requestContent.getParameter(FIRST_NAME_PARAM);
        String lastName = requestContent.getParameter(LAST_NAME_PARAM);
        User user = new User(login, password);
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        return user;
    }
}
