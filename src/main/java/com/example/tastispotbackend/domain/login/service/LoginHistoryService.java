package com.example.tastispotbackend.domain.login.service;

import com.example.tastispotbackend.domain.login.mapper.LoginMapper;
import com.example.tastispotbackend.global.entity.User;
import com.example.tastispotbackend.global.entity.UserLoginHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginHistoryService {
    private final LoginMapper loginMapper;

    @Transactional(propagation = Propagation.REQUIRED) // 기존 트랜잭션을 따라감
    public void insertSuccessHistory(User user, String ipAddress, String userAgent) {
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, true, null, ipAddress, userAgent);
        loginMapper.insertUserLoginHistory(userLoginHistory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) // 별도의 트랜잭션으로 처리
    public void insertFailureHistory(User user, String failureReason, String ipAddress, String userAgent) {
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, false, failureReason, ipAddress, userAgent);
        loginMapper.insertUserLoginHistory(userLoginHistory);
    }
}