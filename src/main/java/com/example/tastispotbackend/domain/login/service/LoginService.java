package com.example.tastispotbackend.domain.login.service;

import com.example.tastispotbackend.domain.login.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final LoginMapper loginMapper;
}
