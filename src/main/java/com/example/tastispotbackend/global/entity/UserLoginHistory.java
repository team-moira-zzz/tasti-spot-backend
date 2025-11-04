package com.example.tastispotbackend.global.entity;

import com.example.tastispotbackend.domain.login.dto.request.LoginDeviceInfoRequest;
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

    public UserLoginHistory(User user, Boolean isSuccess, String failureReason, LoginDeviceInfoRequest loginDeviceInfoRequest) {
        this.userId = user.getId();
        this.success = isSuccess;
        this.failureReason = failureReason;
        this.ipAddress = loginDeviceInfoRequest.ipAddress();
        this.userAgent = loginDeviceInfoRequest.userAgent();
        this.deviceType = loginDeviceInfoRequest.deviceType();
        this.loginTime = ZonedDateTime.now();
    }
}
