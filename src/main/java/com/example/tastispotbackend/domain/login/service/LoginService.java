package com.example.tastispotbackend.domain.login.service;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final CookieHandler cookieHandler;
    private final JwtProvider jwtProvider;
    private final LoginMapper loginMapper;
    private final LoginHistoryService loginHistoryService;

    @Transactional
    public LoginResponse login(
            LoginRequest request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        // [1] HttpServletRequest로부터 유저 접속 정보 추출
        String ipAddress = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);

        // [2] 이메일이 존재하지 않으면 예외 처리
        if (loginMapper.selectEmailChk(request.email()) < 1) {
            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        }

        // [3] 이메일로 유저 조회
        User user = loginMapper.selectUserByEmail(request.email());

        // [4-1] 비밀번호가 일치하지 않으면, 로그인 실패 기록 저장 후 예외처리
        // [4-2] 비밀번호가 일치하면, 로그인 성공 기록 저장
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            loginHistoryService.insertFailureHistory(user, ErrorCode.LOGIN_ERROR.name(), ipAddress, userAgent);

            throw new TastiSpotUserException(ErrorCode.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
        } else {
            loginHistoryService.insertSuccessHistory(user, ipAddress, userAgent);
        }

        // [5] JWT 토큰 생성 (AccessToken, RefreshToken)
        TokenResponse tokenResponse = jwtProvider.createTokens(user);

        // [6] RTK 저장
        loginMapper.updateUserWhenLogin(user.getId(), tokenResponse.rtk());

        // [7] ATK는 클라이언트에게 리턴, RTK는 쿠키에 담아서 전송
        cookieHandler.putRtkInCookie(httpServletResponse, tokenResponse.rtk());

        return new LoginResponse(tokenResponse.atk());
    }
}
