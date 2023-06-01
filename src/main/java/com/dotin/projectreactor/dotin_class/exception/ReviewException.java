package com.dotin.projectreactor.dotin_class.exception;

public class ReviewException extends RuntimeException {
    String message;
    public ReviewException(String message) {
        super(message);
        this.message = message;
    }
}
