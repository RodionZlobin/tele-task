package com.rodion.telenor.exception;

public class EmptyParameterException extends RuntimeException {
    public EmptyParameterException() {
    }

    public EmptyParameterException(String message) {
        super(message);
    }
}