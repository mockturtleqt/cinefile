package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import com.epam.web.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.query.SQLMovieQuery.*;

public class MovieDAO extends AbstractDAO<Movie> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String DESCRIPTION = "description";
    private static final String POSTER = "poster";
    private static final String RATING = "rating";
    private static final String GENRE = "genre";

    private static final Logger logger = LogManager.getLogger();

    public MovieDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean create(Movie movie) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_INSERT_MOVIE);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, safeLocalDateToSqlDate(movie.getReleaseDate()));
            preparedStatement.setString(3, movie.getDescription());
            preparedStatement.setString(4, movie.getPoster());
            preparedStatement.setFloat(5, movie.getRating());
            preparedStatement.setString(6, listToString(movie.getGenre()));

            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public Movie findById(int id) throws DAOException {
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

    public boolean update(Movie movie) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_MOVIE);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, safeLocalDateToSqlDate(movie.getReleaseDate()));
            preparedStatement.setString(3, movie.getDescription());
            preparedStatement.setString(4, movie.getPoster());
            preparedStatement.setString(5, listToString(movie.getGenre()));
            preparedStatement.setInt(6, movie.getId());

            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public boolean deleteById(int id) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_DELETE_MOVIE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public List<Movie> findAll() throws DAOException {
        List<Movie> movieList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MOVIES);
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

    public List<Movie> findByNamePart(String movieTitle) throws DAOException {
        List<Movie> movieList = new ArrayList<>();
        movieTitle = movieTitle.toUpperCase();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MOVIES_BY_NAME_PART);
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

    public List<Movie> findByMediaPersonId(int id) throws DAOException {
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

    public List<Movie> findTopMovies() throws DAOException {
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

    private Movie createMovieFromResultSet(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt(ID));
        movie.setTitle(resultSet.getString(TITLE));

        Date date = resultSet.getDate(RELEASE_DATE);
        LocalDate localDate = (date != null) ? date.toLocalDate() : null;
        movie.setReleaseDate(localDate);

        movie.setDescription(resultSet.getString(DESCRIPTION));
        movie.setPoster(resultSet.getString(POSTER));
        movie.setRating(resultSet.getFloat(RATING));

        String genresString = resultSet.getString(GENRE);
        List<GenreType> genres = null;
        if (genresString != null) {
            genres = new ArrayList<>();
            for (String genre : genresString.split(",")) {
                genres.add(GenreType.valueOf(genre.toUpperCase()));
            }
        }
        movie.setGenre(genres);
        return movie;
    }


}
