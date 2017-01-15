package com.epam.web.command;

import com.epam.web.dao.MovieDAO;
import com.epam.web.entity.Movie;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class FindMovieCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);
            String[] movieToFind = requestContent.getRequestParameters().get("movie-to-find");
            if (movieToFind != null) {
                List<Movie> movieList = movieDAO.findMovie(movieToFind[0]);
                Map<String, Object> requestAttributes = requestContent.getRequestAttributes();
                requestAttributes.put("queryName", "Results for " + movieToFind[0] + ":");
                requestAttributes.put("movie", movieList);
                requestContent.setRequestAttributes(requestAttributes);
            }
            connectionPool.closeConnection(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }

        return ConfigurationManager.getProperty("path.page.base");
    }
}
