package com.example.tastispotbackend.domain.profile.dto.response;

import java.time.ZonedDateTime;

public record ProfileResponse(
        String userId,
        String role,
        String name,
        String nickname,
        String email,
        ZonedDateTime lastLoginAt,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
