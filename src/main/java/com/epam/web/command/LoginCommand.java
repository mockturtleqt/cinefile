package com.epam.web.command;

import com.epam.web.dao.UserDAO;
import com.epam.web.entity.User;
import com.epam.web.jdbc.ConnectionPool;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN = "known-login";
    private static final String PASSWORD = "known-password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            User user = userDAO.findUser(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                page = ConfigurationManager.getProperty("path.page.index");
            } else {
                request.setAttribute("errorLoginPassMsg", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
