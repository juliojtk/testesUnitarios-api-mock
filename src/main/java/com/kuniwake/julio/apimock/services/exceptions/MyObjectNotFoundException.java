package com.kuniwake.julio.apimock.services.exceptions;

public class MyObjectNotFoundException extends RuntimeException{

    public MyObjectNotFoundException(String message) {
        super(message);
    }
}
