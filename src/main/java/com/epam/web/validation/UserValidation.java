package com.epam.web.validation;

import com.epam.web.entity.User;

import java.util.regex.Pattern;

public class UserValidation implements Validation<User> {
    private static final String LOGIN_PATTERN = "^([a-zA-Z]+)[a-zA-Z\\d_]{4,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    public boolean isValid(User user) {
        boolean loginIsValid = user.getLogin().matches(LOGIN_PATTERN);
        boolean passwordIsValid = user.getPassword().matches(PASSWORD_PATTERN);
        return (loginIsValid && passwordIsValid);
    }
}
