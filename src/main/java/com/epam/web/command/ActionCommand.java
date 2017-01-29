package com.epam.web.command;

import com.epam.web.exception.NoSuchPageException;
import com.epam.web.requestContent.SessionRequestContent;

import java.util.List;

public interface ActionCommand {

    String execute(SessionRequestContent requestContent);
}
