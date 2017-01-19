package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.Review;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_MEDIA_PEOPLE_BY_MOVIE_ID;
import static com.epam.web.dbConnection.SQLQueries.SQL_SELECT_REVIEWS_BY_MOVIE_ID;

public class MediaPersonDAO extends AbstractDAO<MediaPerson> {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final Logger logger = LogManager.getLogger();

    public MediaPersonDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean add(MediaPerson mediaPerson) {
        return true;
    }

    public List<MediaPerson> findByMovieId(int id) {
        PreparedStatement preparedStatement = null;
        List<MediaPerson> crew = new ArrayList<>();
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MEDIA_PEOPLE_BY_MOVIE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                crew.add(this.createMediaPersonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return crew;
    }

    private MediaPerson createMediaPersonFromResultSet(ResultSet resultSet) throws SQLException {
        MediaPerson mediaPerson = new MediaPerson();
        mediaPerson.setId(resultSet.getInt(ID));
        mediaPerson.setFirstName(resultSet.getString(FIRST_NAME));
        mediaPerson.setLastName(resultSet.getString(LAST_NAME));
        return mediaPerson;
    }
}
