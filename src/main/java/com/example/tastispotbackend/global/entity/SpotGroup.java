package com.example.tastispotbackend.global.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

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
}
