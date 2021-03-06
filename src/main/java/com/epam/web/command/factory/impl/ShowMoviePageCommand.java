package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.Movie;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MovieService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMoviePageCommand implements ActionCommand {
    private static final String MOVIE_PAGE_PATH = "path.page.movie";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ID_PARAM = "movieId";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String MOVIE_ATTR = "moviePage";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            Movie movie = movieService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(MOVIE_ATTR, movie);
            page = ConfigurationManager.getProperty(MOVIE_PAGE_PATH);
        } catch (NoSuchPageException | ServiceException | InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
