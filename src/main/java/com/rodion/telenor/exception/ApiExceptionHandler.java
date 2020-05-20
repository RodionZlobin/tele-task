package com.rodion.telenor.exception;

import com.rodion.telenor.domain.ApiError;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmptyParameterException.class)
    public ResponseEntity<ApiError> emptyParameterException(EmptyParameterException e) {
        String message = e.getMessage() == null ? "Empty request parameters" : e.getMessage();
        ApiError apiError = ApiError.newBuilder().withMessage(message).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiError> wrongParameterException(InvalidDataAccessApiUsageException e) {
        String message = e.getMessage() == null ? "Wrong request" : e.getMessage();
        ApiError apiError = ApiError.newBuilder().withMessage(message).build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}