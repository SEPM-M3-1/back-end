package com.sepm.exception;

public class StaffAlreadyExistException  extends RuntimeException{
    public StaffAlreadyExistException (String message) {
        super(message);
    }
}
