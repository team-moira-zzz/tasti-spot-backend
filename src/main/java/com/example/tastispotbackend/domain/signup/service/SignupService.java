package com.example.tastispotbackend.domain.signup.service;

import com.example.tastispotbackend.domain.signup.dto.request.SignupRequest;
import com.example.tastispotbackend.domain.signup.mapper.SignupMapper;
import com.example.tastispotbackend.global.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignupService {
    private final SignupMapper signupMapper;

    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {
        int result = signupMapper.selectNicknameChk(nickname);

        // TODO: 예외 처리
    }

    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        int result = signupMapper.selectEmailChk(email);

        // TODO: 예외 처리
    }

    @Transactional
    public void signup(SignupRequest request) {
        User user = new User(request);

        signupMapper.insertUser(user);
    }
}
