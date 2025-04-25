package com.Backend.exceptions.runtime;

public class AppRuntimeException extends RuntimeException {
    public AppRuntimeException(String message){
        super(message);
    }
}
