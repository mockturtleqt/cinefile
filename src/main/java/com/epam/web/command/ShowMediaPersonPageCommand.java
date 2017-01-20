package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMediaPersonPageCommand implements ActionCommand {
    private static final String MEDIA_PERSON_PAGE_PATH = "path.page.media.person";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ID = "mediaPersonId";
    private static final String MEDIA_PERSON = "mediaPersonPage";

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            int id = Integer.valueOf(content.getParameter(ID));
            MediaPersonService mediaPersonService = new MediaPersonService();
            MediaPerson mediaPerson = mediaPersonService.findById(id);

            content.setAttribute(MEDIA_PERSON, mediaPerson);
            page = ConfigurationManager.getProperty(MEDIA_PERSON_PAGE_PATH);

        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        } catch (NoSuchPageException e) {
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
