package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMoviePageCommand implements ActionCommand {
    private static final String MOVIE_PAGE_PATH = "path.page.movie";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ID_PARAM = "movieId";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;

        try {
            int id = Integer.valueOf(requestContent.getParameter(ID_PARAM));
            Movie movie = movieService.findById(id);
            requestContent.setAttribute("moviePage", movie);
            page = ConfigurationManager.getProperty(MOVIE_PAGE_PATH);

        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        } catch (NoSuchPageException e) {
            logger.log(Level.ERROR, e);
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return page;
    }
}
