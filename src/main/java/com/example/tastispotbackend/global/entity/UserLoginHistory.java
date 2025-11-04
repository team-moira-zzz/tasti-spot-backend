package com.example.tastispotbackend.global.entity;

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
    private ZonedDateTime loginTime;

    public UserLoginHistory(User user, Boolean isSuccess, String failureReason, String ipAddress, String userAgent) {
        this.id = null;
        this.userId = user.getId();
        this.success = isSuccess;
        this.failureReason = failureReason;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.loginTime = ZonedDateTime.now();
    }
}
