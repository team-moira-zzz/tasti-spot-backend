package com.example.tastispotbackend.domain.login.controller;

import com.example.tastispotbackend.domain.login.service.LogoutService;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.auth.UserPrincipal;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LogoutController {
    private final LogoutService logoutService;

    @PostMapping("/logout")
    ResponseEntity<Object> logout(
            @UserPrincipal SimpleUserAuth simpleUserAuth,
            HttpServletResponse httpServletResponse
    ) {
        logoutService.logout(simpleUserAuth, httpServletResponse);

        return ResponseEntity.ok(null);
    }
}
