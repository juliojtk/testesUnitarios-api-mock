package com.kuniwake.julio.apimock.services.exceptions;

public class CustomObjectNotFoundException extends RuntimeException{

    public CustomObjectNotFoundException(String message) {
        super(message);
    }
}
