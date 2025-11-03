package com.example.tastispotbackend.global.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserLoginHistory {
    private Long id;
    private String userId;
    private ZonedDateTime loginTime;
    private Boolean success;
    private String ipAddress;
    private String userAgent;
    private String failureReason;
    private String deviceType;
}
