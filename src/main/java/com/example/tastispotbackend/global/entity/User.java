package com.example.tastispotbackend.global.entity;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {
    private String id;
    private UserRole role;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String rtk;
    private ZonedDateTime lastLoginAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public User(SignupRequest request, BCryptPasswordEncoder passwordEncoder) {
        this.id = UUID.randomUUID().toString();
        this.role = UserRole.USER;
        this.name = request.name();
        this.nickname = request.nickname();
        this.email = request.email();
        this.password = passwordEncoder.encode(request.password());
        this.rtk = null;
        this.lastLoginAt = null;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}