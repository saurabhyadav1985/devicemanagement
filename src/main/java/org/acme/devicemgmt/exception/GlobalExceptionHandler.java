package org.acme.devicemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}