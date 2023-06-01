package com.dotin.projectreactor.dotin_class.exception;

public class ReactorException extends Throwable {
    private Throwable exception;
    private String message;

    public ReactorException(Throwable exception, String message) {
        this.exception = exception;
        this.message = message;
    }
}
