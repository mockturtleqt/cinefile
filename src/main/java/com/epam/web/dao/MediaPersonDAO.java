package com.epam.web.dao;

import com.epam.web.dao.exception.DAOException;
import com.epam.web.domain.MediaPerson;
import com.epam.web.domain.type.GenderType;
import com.epam.web.domain.type.OccupationType;
import com.epam.web.util.dbConnection.ProxyConnection;
import com.epam.web.util.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.util.dbConnection.query.SQLMediaPersonQuery.*;

public class MediaPersonDAO extends AbstractDAO<MediaPerson> {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIO = "bio";
    private static final String OCCUPATION = "occupation";
    private static final String GENDER = "gender";
    private static final String BIRTHDAY = "birthday";
    private static final String PICTURE = "picture";
    private static final String CREATE_MEDIA_PERSON_ERROR_MSG = "msg.create.media.person.error";
    private static final String FIND_MEDIA_PERSON_ERROR_MSG = "msg.find.media.person.error";
    private static final String DELETE_MEDIA_PERSON_ERROR_MSG = "msg.delete.media.person.error";
    private static final String UPDATE_MEDIA_PERSON_ERROR_MSG = "msg.update.media.person.error";

    private static final Logger logger = LogManager.getLogger();

    public MediaPersonDAO(ProxyConnection connection) {
        super(connection);
    }

    public MediaPerson create(MediaPerson mediaPerson) throws DAOException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MEDIA_PERSON, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, mediaPerson.getFirstName());
            preparedStatement.setString(2, mediaPerson.getLastName());
            preparedStatement.setString(3, mediaPerson.getBio());
            preparedStatement.setString(4, listToString(mediaPerson.getOccupation()));
            preparedStatement.setString(5, safeEnumToString(mediaPerson.getGender()));
            preparedStatement.setDate(6, safeLocalDateToSqlDate(mediaPerson.getBirthday()));
            preparedStatement.setString(7, mediaPerson.getPicture());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                mediaPerson.setId(generatedKeys.getInt(1));
            }
            return mediaPerson;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(CREATE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public MediaPerson findById(int id) throws DAOException {
        MediaPerson mediaPerson = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MEDIA_PERSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mediaPerson = this.createMediaPersonFromResultSet(resultSet);
            }
            return mediaPerson;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public MediaPerson update(MediaPerson mediaPerson) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MEDIA_PERSON)) {
            preparedStatement.setString(1, mediaPerson.getFirstName());
            preparedStatement.setString(2, mediaPerson.getLastName());
            preparedStatement.setString(3, mediaPerson.getBio());
            preparedStatement.setString(4, listToString(mediaPerson.getOccupation()));
            preparedStatement.setString(5, safeEnumToString(mediaPerson.getGender()));
            preparedStatement.setDate(6, safeLocalDateToSqlDate(mediaPerson.getBirthday()));
            preparedStatement.setString(7, mediaPerson.getPicture());
            preparedStatement.setInt(8, mediaPerson.getId());
            preparedStatement.executeUpdate();
            return mediaPerson;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(UPDATE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_MEDIA_PERSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(DELETE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public List<MediaPerson> findAll() throws DAOException {
        List<MediaPerson> mediaPeople = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MEDIA_PEOPLE);
            while (resultSet.next()) {
                mediaPeople.add(createMediaPersonFromResultSet(resultSet));
            }
            return mediaPeople;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public List<MediaPerson> findByMovieId(int id) throws DAOException {
        List<MediaPerson> crew = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MEDIA_PEOPLE_BY_MOVIE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                crew.add(createMediaPersonFromResultSet(resultSet));
            }
            return crew;
        } catch (SQLException e) {
            throw new DAOException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        }
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
