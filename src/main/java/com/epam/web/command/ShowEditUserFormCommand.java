package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowEditUserFormCommand implements ActionCommand {
    private static final String EDIT_USER_FORM_PATH = "path.page.edit.user";
    private static final String ID = "userId";
    private static final String USER_ATTR = "user";

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        try {
            String idParam = requestContent.getParameter(ID);
            int id = Integer.valueOf(idParam);
            UserService userService = new UserService();
            User user = userService.findById(id);
            requestContent.setAttribute(USER_ATTR, user);
        } catch (NoSuchRequestParameterException | InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return ConfigurationManager.getProperty(EDIT_USER_FORM_PATH);
    }
}
