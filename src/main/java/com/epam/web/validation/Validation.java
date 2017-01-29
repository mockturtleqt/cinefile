package com.epam.web.validation;

import com.epam.web.entity.Entity;

public interface Validation<T extends Entity> {
    public boolean isValid(T entity);
}
