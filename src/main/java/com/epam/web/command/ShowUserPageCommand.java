package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.UserService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowUserPageCommand implements ActionCommand {
    private static final String ID_PARAM = "userId";
    private static final String USER_ATTR = "userPage";
    private static final String USER_PAGE_PATH = "path.page.user";

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            int id = Integer.valueOf(requestContent.getParameter(ID_PARAM));
            User user = userService.findById(id);
            requestContent.setAttribute(USER_ATTR, user);
            page = ConfigurationManager.getProperty(USER_PAGE_PATH);
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
