package com.Backend.users.UsersException;

import com.Backend.exceptions.runtime.AppRuntimeException;

public class AppUserNotFoundException extends AppRuntimeException {
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
