package com.Backend.exceptions;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppNotFoundException extends AppRuntimeException {
    public AppNotFoundException(String message) {
        super(message);
    }
}
