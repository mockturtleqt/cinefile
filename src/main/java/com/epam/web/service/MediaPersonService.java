package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.exception.DAOException;
import com.epam.web.domain.MediaPerson;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.dbConnection.ConnectionPool;
import com.epam.web.util.dbConnection.ProxyConnection;
import com.epam.web.util.resource.MessageManager;

import java.sql.SQLException;
import java.util.List;

public class MediaPersonService extends AbstractService<MediaPerson> {
    private static final String CREATE_MEDIA_PERSON_ERROR_MSG = "msg.create.media.person.error";
    private static final String FIND_MEDIA_PERSON_ERROR_MSG = "msg.find.media.person.error";
    private static final String DELETE_MEDIA_PERSON_ERROR_MSG = "msg.delete.media.person.error";
    private static final String UPDATE_MEDIA_PERSON_ERROR_MSG = "msg.update.media.person.error";

    public MediaPerson create(MediaPerson mediaPerson) throws ServiceException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            return mediaPersonDAO.create(mediaPerson);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public MediaPerson findById(int id) throws ServiceException, InterruptedException, NoSuchPageException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            MediaPerson mediaPerson = mediaPersonDAO.findById(id);

            if (mediaPerson != null) {
                MovieDAO movieDAO = new MovieDAO(connection);
                mediaPerson.setMovies(movieDAO.findByMediaPersonId(id));
            } else {
                throw new NoSuchPageException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG));
            }
            return mediaPerson;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public MediaPerson update(MediaPerson mediaPerson) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            return mediaPersonDAO.update(mediaPerson);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            return mediaPersonDAO.deleteById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MEDIA_PERSON_ERROR_MSG), e);
        }
    }

    public List<MediaPerson> findAll() throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            return mediaPersonDAO.findAll();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        }
    }
}
