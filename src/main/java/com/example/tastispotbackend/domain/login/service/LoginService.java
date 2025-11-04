package com.example.tastispotbackend.domain.login.service;

import com.example.tastispotbackend.domain.login.dto.request.LoginDeviceInfoRequest;
import com.example.tastispotbackend.domain.login.dto.request.LoginRequest;
import com.example.tastispotbackend.domain.login.dto.response.LoginResponse;
import com.example.tastispotbackend.domain.login.mapper.LoginMapper;
import com.example.tastispotbackend.global.auth.JwtProvider;
import com.example.tastispotbackend.global.auth.TokenResponse;
import com.example.tastispotbackend.global.entity.User;
import com.example.tastispotbackend.global.entity.UserLoginHistory;
import com.example.tastispotbackend.global.exception.ErrorCode;
import com.example.tastispotbackend.global.exception.custom.TastiSpotUserException;
import com.example.tastispotbackend.global.utility.CookieHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CookieHandler cookieHandler;
    private final JwtProvider jwtProvider;
    private final LoginMapper loginMapper;

    private LoginDeviceInfoRequest getLoginInfo(HttpServletRequest httpServletRequest) {
        String ipAddress = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        String deviceTypeHeader = httpServletRequest.getHeader("X-Device-Type");

        System.out.println(ipAddress);
        System.out.println(userAgent);
        System.out.println(deviceTypeHeader);

        return new LoginDeviceInfoRequest(ipAddress, userAgent, ""); // TODO: DEVICE_TYPE
    }

    private void checkEmail(String email) {
        int count = loginMapper.selectEmailChk(email);

        if (count < 1) {
            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    private void checkPasswordMatch(String inputPassword, User user, LoginDeviceInfoRequest loginDeviceInfoRequest) {
        if (!bCryptPasswordEncoder.matches(inputPassword, user.getPassword())) {
            this.insertUserLoginHistory(user, false, ErrorCode.LOGIN_ERROR.name(), loginDeviceInfoRequest);

            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    private void insertUserLoginHistory(User user, Boolean isSuccess, String failureReason, LoginDeviceInfoRequest loginDeviceInfoRequest) {
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, isSuccess, failureReason, loginDeviceInfoRequest);
        loginMapper.insertUserLoginHistory(userLoginHistory);
    }

    @Transactional
    public LoginResponse login(
            LoginRequest request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        // [1] HttpServletRequest로부터 유저 접속 정보 추출
        LoginDeviceInfoRequest loginDeviceInfoRequest = this.getLoginInfo(httpServletRequest);

        // [2] 이메일이 존재하지 않으면 예외 처리
        this.checkEmail(request.email());

        // [3] 이메일로 유저 조회
        User user = loginMapper.selectUserByEmail(request.email());

        // [4] 비밀번호가 일치하지 않으면 예외 처리 + 로그인 실패 기록 저장
        this.checkPasswordMatch(request.password(), user, loginDeviceInfoRequest);

        // [5] 로그인 성공 기록 저장
        this.insertUserLoginHistory(user, true, null, loginDeviceInfoRequest);

        // [6] JWT 토큰 생성
        TokenResponse tokenResponse = jwtProvider.createTokens(user);

        // TODO: [7] Redis에 RTK 대입

        // [8] ATK는 클라이언트에게 리턴, RTK는 쿠키에 담아서 전송
        cookieHandler.putRtkInCookie(httpServletResponse, tokenResponse.rtk());

        return new LoginResponse(tokenResponse.atk());
    }
}
