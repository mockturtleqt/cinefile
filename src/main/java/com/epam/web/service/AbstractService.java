package com.epam.web.service;

import com.epam.web.entity.Entity;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;

public abstract class AbstractService<T extends Entity> {

    public abstract T create(T entity) throws ServiceException, InterruptedException, ValidationException;

    public abstract T findById(int id) throws ServiceException, InterruptedException, NoSuchPageException;

    public abstract T update(T entity) throws ServiceException, InterruptedException, ValidationException;

    public abstract boolean deleteById(int id) throws ServiceException, InterruptedException;
}
