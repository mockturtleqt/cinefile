package com.epam.web.command.factory.impl.user;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.User;
import com.epam.web.domain.type.GenderType;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.UserService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowEditUserFormCommand implements ActionCommand {
    private static final String EDIT_USER_FORM_PATH = "path.page.edit.user";
    private static final String ID_PARAM = "userId";
    private static final String USER_ATTR = "user";
    private static final String GENDER_TYPE_ATTR = "genderType";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            requestContent.setAttribute(GENDER_TYPE_ATTR, GenderType.values());
            User user = userService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(USER_ATTR, user);
            page = ConfigurationManager.getProperty(EDIT_USER_FORM_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
