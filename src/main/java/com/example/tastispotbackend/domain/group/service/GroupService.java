package com.example.tastispotbackend.domain.group.service;

import com.example.tastispotbackend.domain.group.component.InvitationCodeGenerator;
import com.example.tastispotbackend.domain.group.dto.request.GroupAddRequest;
import com.example.tastispotbackend.domain.group.dto.response.GroupResponse;
import com.example.tastispotbackend.domain.group.mapper.GroupMapper;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.entity.Group;
import com.example.tastispotbackend.global.entity.GroupMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupMapper groupMapper;

    @Transactional(readOnly = true)
    public List<GroupResponse> getGroupList(SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();

        return groupMapper.selectGroupList(userId);
    }

    @Transactional
    public void addGroup(SimpleUserAuth simpleUserAuth, GroupAddRequest request) {
        String userId = simpleUserAuth.userId();

        // [1] 유니크한 초대 코드 생성
        String invitationCode = "";

        do {
            invitationCode = InvitationCodeGenerator.generateUniqueCode();
        } while (groupMapper.selectInvitationCodeChk(invitationCode) >= 1);

        // [2] 그룹 생성
        Group group = new Group(userId, invitationCode, request);
        groupMapper.insertGroup(group);

        // [3] 그룹 가입
        GroupMember groupMember = new GroupMember(group.getId(), userId);
        groupMapper.insertGroupMember(groupMember);
    }
}
