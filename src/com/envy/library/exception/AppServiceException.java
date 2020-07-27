package com.envy.library.exception;

public class AppServiceException extends Exception{
    public AppServiceException() {
        super();
    }

    public AppServiceException(String message) {
        super(message);
    }

    public AppServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppServiceException(Throwable cause) {
        super(cause);
    }
}