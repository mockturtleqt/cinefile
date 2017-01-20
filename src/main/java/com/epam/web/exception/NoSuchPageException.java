package com.epam.web.exception;

public class NoSuchPageException extends Exception {
    public NoSuchPageException() {
        super();
    }

    public NoSuchPageException(String message) {
        super(message);
    }

    public NoSuchPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPageException(Throwable cause) {
        super(cause);
    }
}
