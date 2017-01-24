package com.epam.web.exception;

public class NoSuchRequestParameterException extends Exception {
    public NoSuchRequestParameterException() {
        super();
    }

    public NoSuchRequestParameterException(String message) {
        super(message);
    }

    public NoSuchRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRequestParameterException(Throwable cause) {
        super(cause);
    }
}
