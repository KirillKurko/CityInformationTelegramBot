package com.kirillkurko.cityinformation.service.aspects;

import com.kirillkurko.cityinformation.service.exceptions.CityNotFoundException;
import com.kirillkurko.cityinformation.service.model.beans.CityErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CityErrorResponse> handleException(CityNotFoundException exception) {
        CityErrorResponse studentErrorResponse = new CityErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CityErrorResponse> handleException(Exception exception) {
        CityErrorResponse studentErrorResponse = new CityErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
