package com.example.tastispotbackend.global.exception;

import java.time.ZonedDateTime;

public record ErrorResponse(
        String message,
        ErrorCode errorCode,
        ZonedDateTime time
) {
}
