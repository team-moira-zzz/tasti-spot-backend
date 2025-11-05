package com.example.tastispotbackend.global.entity;

import com.example.tastispotbackend.domain.group.dto.request.GroupAddRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class SpotGroup {
    private String id;
    private String ownerId;
    private String name;
    private String description;
    private String invitationCode;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public SpotGroup(String userId, String invitationCode, GroupAddRequest request) {
        this.id = UUID.randomUUID().toString();
        this.ownerId = userId;
        this.invitationCode = invitationCode;
        this.name = request.name();
        this.description = request.description();
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}
