package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.exception.NoSuchPageException;

public class MediaPersonService extends AbstractService<MediaPerson> {
    public boolean add(MediaPerson mediaPerson) {
        boolean success = false;
        return success;
    }

    public MediaPerson findById(int id) throws InterruptedException, NoSuchPageException {
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
                throw new NoSuchPageException();
            }

        } finally {
            super.returnConnection(connection);
        }
        return mediaPerson;
    }
}
