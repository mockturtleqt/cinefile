package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.Movie;
import com.epam.web.exception.NoSuchPageException;

import java.util.ArrayList;
import java.util.List;

public class MediaPersonService extends AbstractService<MediaPerson> {

    public boolean create(MediaPerson mediaPerson) throws InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.create(mediaPerson);
        } finally {
            super.returnConnection(connection);
        }
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

    public boolean update(MediaPerson mediaPerson) throws InterruptedException {
        ProxyConnection connection = null;
        boolean success = false;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.update(mediaPerson);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            success = mediaPersonDAO.deleteById(id);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public List<MediaPerson> findAll() throws InterruptedException {
        List<MediaPerson> mediaPeople = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
            mediaPeople = mediaPersonDAO.findAll();
        } finally {
            super.returnConnection(connection);
        }
        return mediaPeople;
    }
}
