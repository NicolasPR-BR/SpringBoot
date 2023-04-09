package com.code.demo.rest;

import com.code.demo.entity.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Best practice
@ControllerAdvice
public class StudentRestExceptionHandler {
    @ExceptionHandler
    //ResponseEntity tells spring this is an exception handler
    //<StudentErrorResponse> tell what type of response
    // StudentNotFoundException tells which type of exception this handles
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e){
        StudentErrorResponse err = new StudentErrorResponse();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    // Gotta catch 'em all, handles all types of exceptions
    public ResponseEntity<StudentErrorResponse> handleException(Exception e){
        StudentErrorResponse err = new StudentErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(e.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
