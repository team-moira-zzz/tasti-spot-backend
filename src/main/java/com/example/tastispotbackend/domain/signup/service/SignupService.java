package com.example.tastispotbackend.domain.signup.service;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import com.example.tastispotbackend.domain.signup.mapper.SignupMapper;
import com.example.tastispotbackend.global.entity.User;
import com.example.tastispotbackend.global.exception.ErrorCode;
import com.example.tastispotbackend.global.exception.custom.TastiSpotUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignupService {
    private final PasswordEncoder passwordEncoder;
    private final SignupMapper signupMapper;

    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {
        int result = signupMapper.selectNicknameChk(nickname);

        if (result > 0) {
            throw new TastiSpotUserException(ErrorCode.ALREADY_USING_NICKNAME, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        int result = signupMapper.selectEmailChk(email);

        if (result > 0) {
            throw new TastiSpotUserException(ErrorCode.ALREADY_USING_EMAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void signup(SignupRequest request) {
        User user = new User(request, passwordEncoder);

        signupMapper.insertUser(user);
    }
}
