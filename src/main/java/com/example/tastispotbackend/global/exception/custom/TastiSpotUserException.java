package com.example.tastispotbackend.global.exception.custom;

import com.example.tastispotbackend.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TastiSpotUserException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public TastiSpotUserException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());

        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
