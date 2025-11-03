package com.example.tastispotbackend.domain.signup.dto.request;

public record SignupRequest(
        String name,
        String nickname,
        String email,
        String password
) {
}
