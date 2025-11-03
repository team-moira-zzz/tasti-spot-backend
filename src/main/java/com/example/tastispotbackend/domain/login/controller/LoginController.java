package com.example.tastispotbackend.domain.login.controller;

import com.example.tastispotbackend.domain.login.dto.LoginRequest;
import com.example.tastispotbackend.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    void login(@RequestBody LoginRequest request) {

    }
}
