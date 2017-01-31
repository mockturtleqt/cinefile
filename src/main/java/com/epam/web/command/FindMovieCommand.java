package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindMovieCommand implements ActionCommand {
    private static final String MOVIE_TO_FIND_PARAM = "movie-to-find";
    private static final String QUERY_NAME_ATTR = "queryName";
    private static final String MOVIE_ATTR = "movie";
    private static final String BASE_PAGE_PATH = "path.page.base";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            String movieTitle = requestContent.getParameter(MOVIE_TO_FIND_PARAM);
            List<Movie> movieList = movieService.findByNamePart(movieTitle);
            requestContent.setAttribute(QUERY_NAME_ATTR, "Results for " + movieTitle + ":");
            requestContent.setAttribute(MOVIE_ATTR, movieList);
            page = ConfigurationManager.getProperty(BASE_PAGE_PATH);
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return page;
    }
}
