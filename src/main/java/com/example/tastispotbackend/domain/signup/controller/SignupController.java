package com.example.tastispotbackend.domain.signup.controller;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import com.example.tastispotbackend.domain.signup.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/signup")
    ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        signupService.signup(request);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/signup/check/nickname")
    ResponseEntity<Object> checkNickname(@RequestParam String nickname) {
        signupService.checkNickname(nickname);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/signup/check/email")
    ResponseEntity<Object> checkEmail(@RequestParam String email) {
        signupService.checkEmail(email);

        return ResponseEntity.ok().body(null);
    }
}
