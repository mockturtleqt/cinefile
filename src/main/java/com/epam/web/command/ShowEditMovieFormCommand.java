package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
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
            Movie movie = movieService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(MOVIE_ATTR, movie);
            page = ConfigurationManager.getProperty(EDIT_MOVIE_FORM_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
