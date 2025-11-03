package com.example.tastispotbackend.global.entity;

import com.example.tastispotbackend.domain.login.dto.request.LoginInfoRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserLoginHistory {
    private Long id;
    private String userId;
    private Boolean success;
    private String failureReason;
    private String ipAddress;
    private String userAgent;
    private String deviceType;
    private ZonedDateTime loginTime;

    public UserLoginHistory(User user, Boolean isSuccess, String failureReason, LoginInfoRequest loginInfoRequest) {
        this.userId = user.getId();
        this.success = isSuccess;
        this.failureReason = failureReason;
        this.ipAddress = loginInfoRequest.ipAddress();
        this.userAgent = loginInfoRequest.userAgent();
        this.deviceType = loginInfoRequest.deviceType();
        this.loginTime = ZonedDateTime.now();
    }
}
