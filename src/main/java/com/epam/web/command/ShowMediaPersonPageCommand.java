package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
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

    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            int id = Integer.valueOf(requestContent.getParameter(ID_PARAM));
            MediaPerson mediaPerson = mediaPersonService.findById(id);

            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPerson);
            page = ConfigurationManager.getProperty(MEDIA_PERSON_PAGE_PATH);

        } catch (InterruptedException | ServiceException | NoSuchPageException |
                NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
