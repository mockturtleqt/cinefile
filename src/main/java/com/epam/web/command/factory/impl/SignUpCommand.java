package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.User;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ValidationException;
import com.epam.web.service.UserService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL_PARAM = "email";
    private static final String FIRST_NAME_PARAM = "first-name";
    private static final String LAST_NAME_PARAM = "last-name";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private final static Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            User user = userService.create(convertToUser(requestContent));
            requestContent.setSessionAttribute(USER_ATTR, user);
            page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException | ValidationException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private User convertToUser(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        User user = new User();
        user.setLogin(requestContent.getParameter(LOGIN_PARAM));
        user.setPassword(requestContent.getParameter(PASSWORD_PARAM));
        user.setEmail(requestContent.getParameter(EMAIL_PARAM));
        user.setFirstName(requestContent.getParameter(FIRST_NAME_PARAM));
        user.setLastName(requestContent.getParameter(LAST_NAME_PARAM));
        return user;
    }
}
