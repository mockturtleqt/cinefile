package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllMoviesCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String MOVIE = "movie";
    private static final String SORTED_MOVIES_PAGE_PATH = "path.page.sorted.movies";

    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            MovieService movieService = new MovieService();
            List<Movie> movieList = movieService.findAll();
            content.setAttribute(MOVIE, movieList);
            page = ConfigurationManager.getProperty(SORTED_MOVIES_PAGE_PATH);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
