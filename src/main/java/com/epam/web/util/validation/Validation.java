package com.epam.web.util.validation;

import com.epam.web.domain.Entity;

import java.util.List;

public interface Validation<T extends Entity> {
    List<String> getValidationExceptions();

    boolean isValid(T entity);
}
