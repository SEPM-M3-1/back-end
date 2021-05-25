package com.sepm.controllers;

import com.sepm.dtos.ErrorDto;
import com.sepm.exception.StaffAlreadyExistException;
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
        ErrorDto error = new ErrorDto("Staff Already Exist","Staff Already Exist");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
