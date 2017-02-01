package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.resource.MessageManager;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInCommand implements ActionCommand {
    private static final String LOGIN_PARAM = "known-login";
    private static final String PASSWORD_PARAM = "known-password";
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String LOGIN_ERROR_ATTR = "errorLoginPassMsg";
    private static final String LOGIN_ERROR_MSG = "message.loginerror";
    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            String login = requestContent.getParameter(LOGIN_PARAM);
            String password = requestContent.getParameter(PASSWORD_PARAM);
            User user = userService.findByLoginAndPassword(login, password);
            if (user != null) {
                requestContent.setSessionAttribute(USER_ATTR, user);
                Memento memento = Memento.getInstance();
                page = memento.getPreviousPage();
            } else {
                requestContent.setAttribute(LOGIN_ERROR_ATTR, MessageManager.getProperty(LOGIN_ERROR_MSG));
                page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
            }
        } catch (InterruptedException | NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e);
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return page;
    }
}
