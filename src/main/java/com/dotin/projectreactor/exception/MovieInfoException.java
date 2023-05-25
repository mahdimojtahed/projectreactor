package com.dotin.projectreactor.exception;

public class MovieInfoException extends RuntimeException {
    String message;

    public MovieInfoException(String message) {
        super(message);
        this.message = message;

    }
}
