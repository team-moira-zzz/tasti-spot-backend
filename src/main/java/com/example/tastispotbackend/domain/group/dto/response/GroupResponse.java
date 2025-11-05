package com.example.tastispotbackend.domain.group.dto.response;

import java.time.ZonedDateTime;

public record GroupResponse(
        String id,
        String name,
        String description,
        int memberCount,
        ZonedDateTime createdAt
) {
}
