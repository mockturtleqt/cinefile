package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllMediaPeopleCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String MEDIA_PERSON = "mediaPerson";
    private static final String SORTED_MEDIA_PEOPLE_PAGE_PATH = "path.page.sorted.media.people";

    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            MediaPersonService mediaPersonService = new MediaPersonService();
            List<MediaPerson> mediaPeople = mediaPersonService.findAll();
            content.setAttribute(MEDIA_PERSON, mediaPeople);
            page = ConfigurationManager.getProperty(SORTED_MEDIA_PEOPLE_PAGE_PATH);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
