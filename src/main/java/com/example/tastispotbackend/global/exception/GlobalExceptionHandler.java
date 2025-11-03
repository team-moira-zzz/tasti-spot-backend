package com.example.tastispotbackend.global.exception;

import com.example.tastispotbackend.global.exception.custom.TastiSpotUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TastiSpotUserException.class)
    ResponseEntity<ErrorResponse> handleTastiSpotUserException(TastiSpotUserException e) {
        ErrorResponse body = new ErrorResponse(e.getMessage(), e.getErrorCode(), ZonedDateTime.now());

        return ResponseEntity.status(e.getHttpStatus()).body(body);
    }
}
