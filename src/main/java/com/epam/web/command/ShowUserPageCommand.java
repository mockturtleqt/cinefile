package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowUserPageCommand implements ActionCommand {
    private static final String ID = "userId";
    private static final String USER = "userPage";
    private static final String USER_PAGE_PATH = "path.page.user";

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            int id = Integer.valueOf(content.getParameter(ID));
            UserService userService = new UserService();
            User user = userService.findById(id);
            content.setAttribute(USER, user);
            page = ConfigurationManager.getProperty(USER_PAGE_PATH);
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
