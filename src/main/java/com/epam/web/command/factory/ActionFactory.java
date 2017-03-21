package com.epam.web.command.factory;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandEnum;
import com.epam.web.command.factory.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String COMMAND_PARAM = "command";
    private static final Logger logger = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String userCommand = request.getParameter(COMMAND_PARAM);

        if (userCommand == null || userCommand.isEmpty()) {
            return current;
        }

        CommandEnum currentEnum = CommandEnum.valueOf(userCommand.toUpperCase());
        current = currentEnum.getCurrentCommand();

        return current;
    }
}
