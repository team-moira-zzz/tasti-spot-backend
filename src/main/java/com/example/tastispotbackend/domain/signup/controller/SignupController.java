package com.example.tastispotbackend.domain.signup.controller;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import com.example.tastispotbackend.domain.signup.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/signup")
    void signup(@RequestBody SignupRequest request) {

    }

    @GetMapping("/signup/check/nickname")
    void checkNickname(@RequestParam String nickname) {

    }

    @PostMapping("/signup/check/email")
    void checkEmail(@RequestParam String email) {

    }
}
