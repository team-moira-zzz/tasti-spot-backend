package com.example.tastispotbackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 시스템 관련 에러코드
    INTERNAL_SYSTEM_ERROR("S001", "알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    
    // 유저 관련 에러코드
    ALREADY_USING_NICKNAME("U0001", "이미 사용 중인 닉네임입니다."),
    ALREADY_USING_EMAIL("U0002", "이미 사용 중인 이메일입니다."),
    LOGIN_ERROR("U0003", "이메일 혹은 비밀번호를 잘못 입력하였습니다."),

    // 권한 관련 에러코드
    INVALID_AUTHORIZATION_HEADER("A001", "Authorization 헤더에 토큰 정보가 포함되어 있지 않습니다."),
    EXPIRED_ATK("A002", "Access Token이 만료되었습니다."),
    INVALID_TOKEN1("A003", "토큰 서명이 유효하지 않거나 형식이 올바르지 않습니다."),
    INVALID_TOKEN2("A004", "유효하지 않은 토큰입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
