package com.epam.web.dao;

import com.epam.web.dao.exception.DAOException;
import com.epam.web.domain.Entity;
import com.epam.web.util.dbConnection.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    private static final Logger logger = LogManager.getLogger();
    protected ProxyConnection connection;

    public AbstractDAO(ProxyConnection connection) {
        this.connection = connection;
    }
 
    public abstract T create(T entity) throws DAOException;

    public abstract T findById(int id) throws DAOException;

    public abstract T update(T entity) throws DAOException;

    public abstract boolean deleteById(int id) throws DAOException;

    <Type> String listToString(List<Type> items) {
        StringBuilder itemsAsString = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            itemsAsString.append(items.get(i));
            //So that we won't have a comma after the last item
            if (i != items.size() - 1) {
                itemsAsString.append(",");
            }
        }
        return itemsAsString.toString();
    }

    <Type> String safeEnumToString(Type type) {
        return (type != null) ? String.valueOf(type) : null;
    }

    Date safeLocalDateToSqlDate(LocalDate localDate) {
        return (localDate != null) ? Date.valueOf(localDate) : null;
    }

}
