package com.example.BookmyshowApplication.Exceptions;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String message) {
        super(message);
    }
}
