package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.MediaPerson;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MediaPersonService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMediaPersonPageCommand implements ActionCommand {
    private static final String MEDIA_PERSON_PAGE_PATH = "path.page.media.person";
    private static final String ID_PARAM = "mediaPersonId";
    private static final String MEDIA_PERSON_ATTR = "mediaPersonPage";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MediaPersonService mediaPersonService = new MediaPersonService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            MediaPerson mediaPerson = mediaPersonService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPerson);
            page = ConfigurationManager.getProperty(MEDIA_PERSON_PAGE_PATH);

        } catch (InterruptedException | ServiceException | NoSuchPageException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
