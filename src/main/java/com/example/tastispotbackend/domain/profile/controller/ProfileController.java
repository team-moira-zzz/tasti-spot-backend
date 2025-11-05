package com.example.tastispotbackend.domain.profile.controller;

import com.example.tastispotbackend.domain.profile.dto.response.ProfileResponse;
import com.example.tastispotbackend.domain.profile.service.ProfileService;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/me/simple")
    ResponseEntity<SimpleUserAuth> getSimpleProfile(@UserPrincipal SimpleUserAuth simpleUserAuth) {
        return ResponseEntity.ok(simpleUserAuth);
    }

    @GetMapping("/me")
    ResponseEntity<ProfileResponse> getProfile(@UserPrincipal SimpleUserAuth simpleUserAuth) {
        ProfileResponse profileResponse = profileService.getProfile(simpleUserAuth);

        return ResponseEntity.ok(profileResponse);
    }
}
