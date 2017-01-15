package com.epam.web.command;

import com.epam.web.dao.UserDAO;
import com.epam.web.entity.User;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Map;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN = "known-login";
    private static final String PASSWORD = "known-password";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            String[] login = requestContent.getRequestParameters().get(LOGIN);
            String[] password = requestContent.getRequestParameters().get(PASSWORD);
            Map<String, Object> sessionAttributes = requestContent.getSessionAttributes();
            if (login != null && password != null) {
                User user = userDAO.findUser(login[0], password[0]);
                if (user != null) {
                    sessionAttributes.put("user", login[0]);
                    page = ConfigurationManager.getProperty("path.page.index");
                } else {
                    sessionAttributes.put("errorLoginPassMsg", MessageManager.getProperty("message.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            }
            requestContent.setSessionAttributes(sessionAttributes);
            connectionPool.closeConnection(connection);

        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
