package com.epam.web.factory;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandEnum;
import com.epam.web.command.EmptyCommand;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {
    private static final String COMMAND_PARAM = "command";
    private static final String WRONG_ACTION_ATTR = "wrongAction";
    private static final String WRONG_ACTION_MSG = "message.wrongaction";
    private static final Logger logger = LogManager.getLogger();

    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String action = null;
        try {
            action = requestContent.getParameter(COMMAND_PARAM);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            requestContent.setAttribute(WRONG_ACTION_ATTR, action + MessageManager.getProperty(WRONG_ACTION_MSG));
        }
        return current;
    }
}
