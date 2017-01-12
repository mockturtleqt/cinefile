package com.epam.web.dao;

import com.epam.web.entity.Movie;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.jdbc.SQLQueries.SQL_SELECT_MOVIE;

public class MovieDAO extends AbstractDAO<Movie> {
    private static final Logger logger = LogManager.getLogger();
    public MovieDAO(Connection connection) {
        super(connection);
    }

    public List<Movie> findMovie(String titleToFind) {
        List<Movie> movieList = new ArrayList<>();
        titleToFind = titleToFind.toUpperCase();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.getPreparedStatement(SQL_SELECT_MOVIE);
            preparedStatement.setString(1, "%" + titleToFind + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String poster = resultSet.getString("poster");
                Movie movie = new Movie(id, title);
                movie.setDescription(description);
                movie.setPoster(poster);
                movieList.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            this.close(preparedStatement);
        }
        return movieList;
    }
}
