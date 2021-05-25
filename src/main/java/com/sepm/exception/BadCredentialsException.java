package com.sepm.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException (String message) {
        super(message);
    }
}
