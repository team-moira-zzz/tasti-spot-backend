package com.example.tastispotbackend.domain.group.controller;

import com.example.tastispotbackend.domain.group.dto.request.GroupAddRequest;
import com.example.tastispotbackend.domain.group.dto.response.GroupResponse;
import com.example.tastispotbackend.domain.group.service.GroupService;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/group")
    ResponseEntity<List<GroupResponse>> getGroupList(
            @UserPrincipal SimpleUserAuth simpleUserAuth
    ) {
        List<GroupResponse> list = groupService.getGroupList(simpleUserAuth);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/group")
    ResponseEntity<Object> addGroup(
            @UserPrincipal SimpleUserAuth simpleUserAuth,
            @RequestBody GroupAddRequest request
    ) {
        groupService.addGroup(simpleUserAuth, request);

        return ResponseEntity.ok(null);
    }
}
