package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_ALL_MOVIES_BY_TITLE;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_MOVIE_BY_TITLE;

public class MovieDAO extends AbstractDAO<Movie> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DURATION =  "duration";
    private static final String RELEASE_DATE =  "release_date";
    private static final String DESCRIPTION = "description";
    private static final String POSTER = "poster";
    private static final String RATING =  "rating";
    private static final String GENRE =  "genre";
    private static final Logger logger = LogManager.getLogger();

    public MovieDAO(ProxyConnection connection) {
        super(connection);
    }

    public Movie find(String movieTitle) {
        Movie movie = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIE_BY_TITLE);
            preparedStatement.setString(1, movieTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movie = this.createMovieFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return movie;
    }

    public List<Movie> findAll(String movieTitle) {
        List<Movie> movieList = new ArrayList<>();
        movieTitle = movieTitle.toUpperCase();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_ALL_MOVIES_BY_TITLE);
            preparedStatement.setString(1, "%" + movieTitle + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movieList.add(this.createMovieFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return movieList;
    }

    public boolean add(Movie movie) {
        return true;
    }

    private Movie createMovieFromResultSet(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt(ID));
        movie.setTitle(resultSet.getString(TITLE));
        //movie.setDuration(resultSet.getTime(DURATION));
        movie.setReleaseDate(resultSet.getDate(RELEASE_DATE));
        movie.setDescription(resultSet.getString(DESCRIPTION));
        movie.setPoster(resultSet.getString(POSTER));
        movie.setRating(resultSet.getFloat(RATING));

//        List<GenreType> genres = new ArrayList<>();
//        String[] genresAsStrings = (String[])resultSet.getArray(GENRE).getArray();
//        for (String genre: genresAsStrings) {
//            genres.add(GenreType.valueOf(genre.toUpperCase()));
//        }
//
//        movie.setGenre(genres);
        return movie;
    }
}
