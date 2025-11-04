package com.example.tastispotbackend.domain.login.service;

import com.example.tastispotbackend.domain.login.mapper.LogoutMapper;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.utility.CookieHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogoutService {
    private final CookieHandler cookieHandler;
    private final LogoutMapper logoutMapper;

    @Transactional
    public void logout(SimpleUserAuth simpleUserAuth, HttpServletResponse httpServletResponse) {
        String userId = simpleUserAuth.userId();

        // [1] RTK 제거 (DB)
        logoutMapper.updateUserWhenLogout(userId);

        // [2] RTK 제거 (쿠키)
        cookieHandler.removeRtkFromCookie(httpServletResponse);
    }
}
