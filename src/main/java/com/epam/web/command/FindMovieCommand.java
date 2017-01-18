package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;

import java.util.List;

public class FindMovieCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    private static final String MOVIE_TO_FIND_PARAM = "movie-to-find";
    private static final String QUERY_NAME_ATTR = "queryName";
    private static final String MOVIE_ATTR = "movie";
    private static final String BASE_PAGE_PATH = "path.page.base";

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            String movieTitle = requestContent.getParameter(MOVIE_TO_FIND_PARAM);

            MovieService movieService = new MovieService();
            List<Movie> movieList = movieService.findAll(movieTitle);

            requestContent.setAttribute(QUERY_NAME_ATTR, "Results for " + movieTitle + ":");
            requestContent.setAttribute(MOVIE_ATTR, movieList);
            page = ConfigurationManager.getProperty(BASE_PAGE_PATH);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }

        return page;
    }
}
