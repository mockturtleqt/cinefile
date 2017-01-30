package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ServiceException;
import com.epam.web.resource.MessageManager;

import java.util.ArrayList;
import java.util.List;

public class MediaPersonService extends AbstractService<MediaPerson> {
    private static final String CREATE_MEDIA_PERSON_ERROR_MSG = "msg.create.media.person.error";
    private static final String FIND_MEDIA_PERSON_ERROR_MSG = "msg.find.media.person.error";
    private static final String DELETE_MEDIA_PERSON_ERROR_MSG = "msg.delete.media.person.error";
    private static final String UPDATE_MEDIA_PERSON_ERROR_MSG = "msg.update.media.person.error";

    public boolean create(MediaPerson mediaPerson) throws InterruptedException, ServiceException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.create(mediaPerson);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_MEDIA_PERSON_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public MediaPerson findById(int id) throws ServiceException, InterruptedException, NoSuchPageException {
        ProxyConnection connection = null;
        MediaPerson mediaPerson = null;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            mediaPerson = mediaPersonDAO.findById(id);

            if (mediaPerson != null) {
                MovieDAO movieDAO = new MovieDAO(connection);
                mediaPerson.setMovies(movieDAO.findByMediaPersonId(id));
            } else {
                throw new NoSuchPageException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG));
            }
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return mediaPerson;
    }

    public boolean update(MediaPerson mediaPerson) throws ServiceException, InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.update(mediaPerson);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_MEDIA_PERSON_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_MEDIA_PERSON_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public List<MediaPerson> findAll() throws ServiceException, InterruptedException {
        List<MediaPerson> mediaPeople = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            mediaPeople = mediaPersonDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_MEDIA_PERSON_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return mediaPeople;
    }
}
