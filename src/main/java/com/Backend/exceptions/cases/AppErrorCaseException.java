package com.Backend.exceptions.cases;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppErrorCaseException extends AppRuntimeException {
    public AppErrorCaseException(String message) {
        super(message);
    }
}
