package com.envy.library.exception;

public class AppDaoException extends Exception{
    public AppDaoException() {
        super();
    }

    public AppDaoException(String message) {
        super(message);
    }

    public AppDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppDaoException(Throwable cause) {
        super(cause);
    }
}