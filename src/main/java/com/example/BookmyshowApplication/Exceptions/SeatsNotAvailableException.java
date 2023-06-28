package com.example.BookmyshowApplication.Exceptions;

public class SeatsNotAvailableException extends RuntimeException {
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
