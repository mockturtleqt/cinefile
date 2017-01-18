package com.epam.web.exception;

public class NoSuchDAOClassException extends Exception {
    public NoSuchDAOClassException() {
        super();
    }

    public NoSuchDAOClassException(String message) {
        super(message);
    }

    public NoSuchDAOClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDAOClassException(Throwable cause) {
        super(cause);
    }
}
