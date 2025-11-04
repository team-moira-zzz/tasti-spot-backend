package com.example.tastispotbackend.domain.login.dto.request;

public record LoginDeviceInfoRequest(String ipAddress, String userAgent, String deviceType) {
}
