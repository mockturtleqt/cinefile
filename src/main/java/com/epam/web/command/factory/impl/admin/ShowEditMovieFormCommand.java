package com.epam.web.command.factory.impl.admin;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.Movie;
import com.epam.web.domain.type.GenreType;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MovieService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowEditMovieFormCommand implements ActionCommand {
    private static final String EDIT_MOVIE_FORM_PATH = "path.page.edit.movie";
    private static final String ID_PARAM = "movieId";
    private static final String MOVIE_ATTR = "movie";
    private static final String GENRE_TYPE_ATTR = "genreType";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            requestContent.setAttribute(GENRE_TYPE_ATTR, GenreType.values());
            if (movieExists(requestContent)) {
                Movie movie = movieService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
                requestContent.setAttribute(MOVIE_ATTR, movie);
            }
            page = ConfigurationManager.getProperty(EDIT_MOVIE_FORM_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private boolean movieExists(SessionRequestContent requestContent) {
        try {
            requestContent.getParameter(ID_PARAM);
            return true;
        } catch (NoSuchRequestParameterException e) {
            return false;
        }
    }
}
