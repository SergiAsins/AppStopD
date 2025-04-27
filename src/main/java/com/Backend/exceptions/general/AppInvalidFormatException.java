package com.Backend.exceptions.general;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppInvalidFormatException extends RuntimeException {
    public AppInvalidFormatException(String message) {
        super(message);
    }
}
