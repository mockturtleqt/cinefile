package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.exception.ServiceException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllMoviesCommand implements ActionCommand {
    private static final String MOVIE_ATTR = "movie";
    private static final String SORTED_MOVIES_PAGE_PATH = "path.page.sorted.movies";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<Movie> movieList = movieService.findAll();
            requestContent.setAttribute(MOVIE_ATTR, movieList);
            page = ConfigurationManager.getProperty(SORTED_MOVIES_PAGE_PATH);
        } catch (ServiceException | InterruptedException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
