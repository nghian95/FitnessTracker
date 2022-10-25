package com.nghianguyen.fitnesstracker.exception;

public class IncorrectUserException extends Exception {
    public IncorrectUserException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    
    public IncorrectUserException(String errorMessage) {
        super(errorMessage);
    }
}
