package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;
import com.epam.web.entity.type.GenreType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.SQLQueries.*;

public class MovieDAO extends AbstractDAO<Movie> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DURATION = "duration";
    private static final String RELEASE_DATE = "release_date";
    private static final String DESCRIPTION = "description";
    private static final String POSTER = "poster";
    private static final String RATING = "rating";
    private static final String GENRE = "genre";

    private static final Logger logger = LogManager.getLogger();

    public MovieDAO(ProxyConnection connection) {
        super(connection);
    }

    public Movie findById(int id) {
        Movie movie = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIE_BY_ID);
            preparedStatement.setInt(1, id);
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

    public List<Movie> findByMediaPersonId(int id) {
        List<Movie> movieList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIES_BY_MEDIA_PERSON_ID);
            preparedStatement.setInt(1, id);
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

    public List<Movie> findTopMovies() {
        List<Movie> movieList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_TOP_10_MOVIES);
            while (resultSet.next()) {
                movieList.add(this.createMovieFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(statement);
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
        movie.setReleaseDate(resultSet.getDate(RELEASE_DATE));
        movie.setDescription(resultSet.getString(DESCRIPTION));
        movie.setPoster(resultSet.getString(POSTER));
        movie.setRating(resultSet.getFloat(RATING));

        String genresString = resultSet.getString(GENRE);
        List<GenreType> genres = new ArrayList<>();
        for (String genre : genresString.split(",")) {
            genres.add(GenreType.valueOf(genre.toUpperCase()));
        }

        movie.setGenre(genres);
        return movie;
    }
}
