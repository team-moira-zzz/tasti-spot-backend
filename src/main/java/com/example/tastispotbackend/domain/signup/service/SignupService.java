package com.example.tastispotbackend.domain.signup.service;

import com.example.tastispotbackend.domain.signup.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignupService {
    private final SignupMapper signupMapper;

}
