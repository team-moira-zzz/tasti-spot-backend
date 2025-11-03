package com.example.tastispotbackend.domain.login.controller;

import com.example.tastispotbackend.domain.login.dto.request.LoginRequest;
import com.example.tastispotbackend.domain.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Object> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        loginService.login(request, httpServletRequest);

        return ResponseEntity.ok(null); // 추후 토큰 정보 리턴
    }
}
