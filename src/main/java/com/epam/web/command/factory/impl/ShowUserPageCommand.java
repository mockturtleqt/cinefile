package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.User;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.UserService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowUserPageCommand implements ActionCommand {
    private static final String ID_PARAM = "userId";
    private static final String USER_ATTR = "userPage";
    private static final String USER_PAGE_PATH = "path.page.user";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            User user = userService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(USER_ATTR, user);
            page = ConfigurationManager.getProperty(USER_PAGE_PATH);
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException | NoSuchPageException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
