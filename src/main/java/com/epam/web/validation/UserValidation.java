package com.epam.web.validation;

import com.epam.web.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidation implements Validation<User> {
    private static final String LOGIN_PATTERN = "^([a-zA-Z]+)[a-zA-Z\\d_]{4,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+";

    private List<String> validationExceptions = new ArrayList<>();

    public List<String> getValidationExceptions() {
        return validationExceptions;
    }

    public boolean isValid(User user) {
        return isLoginValid(user.getLogin()) && isPasswordValid(user.getPassword()) && isEmailValid(user.getEmail());
    }

    private boolean isLoginValid(String login) {
        if (login.matches(LOGIN_PATTERN) && login.length() < 45) {
            return true;
        } else {
            validationExceptions.add("User login is not valid.");
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.matches(PASSWORD_PATTERN) && password.length() < 100) {
            return true;
        } else {
            validationExceptions.add("User password is not valid.");
            return false;
        }
    }

    private boolean isEmailValid(String email) {
        if (email.matches(EMAIL_PATTERN) && email.length() < 100) {
            return true;
        } else {
            validationExceptions.add("User email is not valid.");
            return false;
        }
    }
}
