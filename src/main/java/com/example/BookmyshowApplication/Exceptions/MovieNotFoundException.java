package com.example.BookmyshowApplication.Exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
