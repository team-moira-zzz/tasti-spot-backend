package com.example.tastispotbackend.domain.login.dto;

public record LoginRequest(
        String email,
        String password
) {
}
