package com.sg.flooringmastery.service;

public class BadOrderRequestException extends Exception {

    public BadOrderRequestException(String message) {
        super(message);
    }

    public BadOrderRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
