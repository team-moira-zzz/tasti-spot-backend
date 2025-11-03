package com.example.tastispotbackend.global.entity;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public User(SignupRequest request) {
        this.id = UUID.randomUUID().toString();
        this.name = request.name();
        this.nickname = request.nickname();
        this.email = request.email();
        this.password = ""; // TODO: 암호화
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}