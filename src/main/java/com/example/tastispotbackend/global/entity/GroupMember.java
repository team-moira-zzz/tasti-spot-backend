package com.example.tastispotbackend.global.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class GroupMember {
    private Long id;
    private String groupId;
    private String userId;
    private ZonedDateTime joinedAt;
    private ZonedDateTime leftAt;

    public GroupMember(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
        this.joinedAt = ZonedDateTime.now();
        this.leftAt = null;
    }
}
