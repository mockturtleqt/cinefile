package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.OccupationType;
import com.epam.web.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.dbConnection.query.SQLMediaPersonQuery.*;

public class MediaPersonDAO extends AbstractDAO<MediaPerson> {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIO = "bio";
    private static final String OCCUPATION = "occupation";
    private static final String GENDER = "gender";
    private static final String BIRTHDAY = "birthday";
    private static final String PICTURE = "picture";
    private static final Logger logger = LogManager.getLogger();

    public MediaPersonDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean create(MediaPerson mediaPerson) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_INSERT_MEDIA_PERSON);
            preparedStatement.setString(1, mediaPerson.getFirstName());
            preparedStatement.setString(2, mediaPerson.getLastName());
            preparedStatement.setString(3, mediaPerson.getBio());
            preparedStatement.setString(4, listToString(mediaPerson.getOccupation()));
            preparedStatement.setString(5, safeEnumToString(mediaPerson.getGender()));
            preparedStatement.setDate(6, safeLocalDateToSqlDate(mediaPerson.getBirthday()));
            preparedStatement.setString(7, mediaPerson.getPicture());

            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return success;
    }

    public MediaPerson findById(int id) throws DAOException {
        PreparedStatement preparedStatement = null;
        MediaPerson mediaPerson = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_SELECT_MEDIA_PERSON_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mediaPerson = this.createMediaPersonFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(preparedStatement);
        }
        return mediaPerson;
    }

    public boolean update(MediaPerson mediaPerson) throws DAOException {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getPreparedStatement(SQL_UPDATE_MEDIA_PERSON);
            preparedStatement.setString(1, mediaPerson.getFirstName());
            preparedStatement.setString(2, mediaPerson.getLastName());
            preparedStatement.setString(3, mediaPerson.getBio());
            preparedStatement.setString(4, listToString(mediaPerson.getOccupation()));
            preparedStatement.setString(5, safeEnumToString(mediaPerson.getGender()));
            preparedStatement.setDate(6, safeLocalDateToSqlDate(mediaPerson.getBirthday()));
            preparedStatement.setString(7, mediaPerson.getPicture());
            preparedStatement.setInt(8, mediaPerson.getId());

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
            preparedStatement = connection.getPreparedStatement(SQL_DELETE_MEDIA_PERSON_BY_ID);
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

    public List<MediaPerson> findAll() throws DAOException {
        List<MediaPerson> mediaPeople = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MEDIA_PEOPLE);
            while (resultSet.next()) {
                mediaPeople.add(this.createMediaPersonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        } finally {
            connection.closeStatement(statement);
        }
        return mediaPeople;
    }

    public List<MediaPerson> findByMovieId(int id) throws DAOException {
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
        mediaPerson.setBio(resultSet.getString(BIO));

        String occupationsString = resultSet.getString(OCCUPATION);
        List<OccupationType> occupations = null;
        if (occupationsString != null) {
            occupations = new ArrayList<>();
            for (String occupation : occupationsString.split(",")) {
                occupations.add(OccupationType.valueOf(occupation.toUpperCase()));
            }
        }
        mediaPerson.setOccupation(occupations);

        String genderString = resultSet.getString(GENDER);
        GenderType genderType = (genderString != null) ? GenderType.valueOf(genderString.toUpperCase()) : null;
        mediaPerson.setGender(genderType);

        Date date = resultSet.getDate(BIRTHDAY);
        LocalDate localDate = (date != null) ? date.toLocalDate() : null;
        mediaPerson.setBirthday(localDate);

        mediaPerson.setPicture(resultSet.getString(PICTURE));
        return mediaPerson;
    }
}
