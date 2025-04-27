package com.Backend.exceptions.general;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppNotFoundException extends AppRuntimeException {
    public AppNotFoundException(String message) {
        super(message);
    }
}
