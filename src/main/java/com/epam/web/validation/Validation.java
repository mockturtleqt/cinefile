package com.epam.web.validation;

import com.epam.web.entity.Entity;

import java.util.List;

public interface Validation<T extends Entity> {
    List<String> getValidationExceptions();

    boolean isValid(T entity);
}
