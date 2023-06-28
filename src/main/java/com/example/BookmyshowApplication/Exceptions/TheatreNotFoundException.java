package com.example.BookmyshowApplication.Exceptions;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String message) {
        super(message);
    }
}
