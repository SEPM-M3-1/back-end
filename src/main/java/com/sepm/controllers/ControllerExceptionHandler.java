package com.sepm.controllers;

import com.sepm.dtos.ErrorDto;
import com.sepm.exception.BadCredentialsException;
import com.sepm.exception.ManagerNotFundException;
import com.sepm.exception.StaffAlreadyExistException;
import com.sepm.exception.StaffNotFundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {StaffAlreadyExistException.class})
    public ResponseEntity<ErrorDto> handleStaffAlreadyExistException(StaffAlreadyExistException e) {
        log.info("User is not found.", e);
        ErrorDto error = new ErrorDto("Staff Already Exist", e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {StaffNotFundException.class})
    public ResponseEntity<ErrorDto> handleStaffNotFoundException(StaffNotFundException e) {
        ErrorDto errorDto = new ErrorDto("No such staff found", e.getLocalizedMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ManagerNotFundException.class})
    public ResponseEntity<ErrorDto> handleManagerNotFoundException(ManagerNotFundException e) {
        ErrorDto errorDto = new ErrorDto("No such manager found", e.getLocalizedMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorDto> handleBadCredentialsException(BadCredentialsException e) {
        ErrorDto errorDto = new ErrorDto("The email or password is incorrect", e.getLocalizedMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
