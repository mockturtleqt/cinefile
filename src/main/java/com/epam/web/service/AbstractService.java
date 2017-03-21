package com.epam.web.service;

import com.epam.web.domain.Entity;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.ValidationException;
import com.epam.web.service.exception.ServiceException;

public abstract class AbstractService<T extends Entity> {

    public abstract T create(T entity) throws ServiceException, InterruptedException, ValidationException;

    public abstract T findById(int id) throws ServiceException, InterruptedException, NoSuchPageException;

    public abstract T update(T entity) throws ServiceException, InterruptedException, ValidationException;

    public abstract boolean deleteById(int id) throws ServiceException, InterruptedException;
}
