package com.example.tastispotbackend.domain.login.service;

import com.example.tastispotbackend.domain.login.dto.request.LoginInfoRequest;
import com.example.tastispotbackend.domain.login.dto.request.LoginRequest;
import com.example.tastispotbackend.domain.login.mapper.LoginMapper;
import com.example.tastispotbackend.global.entity.User;
import com.example.tastispotbackend.global.entity.UserLoginHistory;
import com.example.tastispotbackend.global.exception.ErrorCode;
import com.example.tastispotbackend.global.exception.custom.TastiSpotUserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginMapper loginMapper;

    private LoginInfoRequest getLoginInfo(HttpServletRequest httpServletRequest) {
        String ipAddress = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        String deviceTypeHeader = httpServletRequest.getHeader("X-Device-Type");

        System.out.println(ipAddress);
        System.out.println(userAgent);
        System.out.println(deviceTypeHeader);

        return new LoginInfoRequest(ipAddress, userAgent, ""); // TODO: DEVICE_TYPE
    }

    private void checkEmail(String email) {
        int count = loginMapper.selectEmailChk(email);

        if (count < 1) {
            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    private void checkPasswordMatch(String inputPassword, User user, LoginInfoRequest loginInfoRequest) {
        if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
            this.insertUserLoginHistory(user, false, ErrorCode.LOGIN_ERROR.name(), loginInfoRequest);

            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    private void insertUserLoginHistory(User user, Boolean isSuccess, String failureReason, LoginInfoRequest loginInfoRequest) {
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, isSuccess, failureReason, loginInfoRequest);
        loginMapper.insertUserLoginHistory(userLoginHistory);
    }

    @Transactional
    public void login(LoginRequest request, HttpServletRequest httpServletRequest) {
        // [1] HttpServletRequest로부터 유저 접속 정보 추출
        LoginInfoRequest loginInfoRequest = this.getLoginInfo(httpServletRequest);

        // [2] 이메일이 존재하지 않으면 예외 처리
        this.checkEmail(request.email());

        // [3] 이메일로 유저 조회
        User user = loginMapper.selectUserByEmail(request.email());

        // [4] 비밀번호가 일치하지 않으면 예외 처리 + 로그인 실패 기록 저장
        this.checkPasswordMatch(request.password(), user, loginInfoRequest);

        // [5] 로그인 성공 기록 저장
        this.insertUserLoginHistory(user, true, null, loginInfoRequest);

        // TODO: [6] JWT 토큰 생성

        // TODO: [7] Redis에 RTK 대입
    }
}
