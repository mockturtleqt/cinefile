package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;

public class MediaPersonDAO extends AbstractDAO<MediaPerson> {

    public MediaPersonDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean add(MediaPerson mediaPerson) {
        return true;
    }
}
