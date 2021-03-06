package com.epam.web.command.factory.impl.user;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MovieRatingService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.memento.Memento;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteMovieRatingCommand implements ActionCommand {
    private static final String ID_PARAM = "rating-id";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieRatingService movieRatingService = new MovieRatingService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            movieRatingService.deleteById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));

            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
