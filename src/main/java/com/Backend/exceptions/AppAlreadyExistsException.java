package com.Backend.exceptions;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppAlreadyExistsException extends RuntimeException {
    public AppAlreadyExistsException(String message) {
        super(message);
    }
}
