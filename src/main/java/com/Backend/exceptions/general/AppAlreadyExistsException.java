package com.Backend.exceptions.general;

public class AppAlreadyExistsException extends RuntimeException {
    public AppAlreadyExistsException(String message) {
        super(message);
    }
}
