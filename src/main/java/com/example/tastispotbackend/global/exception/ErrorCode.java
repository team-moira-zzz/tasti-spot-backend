package com.example.tastispotbackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 유저 관련 에러코드
    ALREADY_USING_NICKNAME("U0001", "이미 사용 중인 닉네임입니다."),
    ALREADY_USING_EMAIL("U0002", "이미 사용 중인 이메일입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
