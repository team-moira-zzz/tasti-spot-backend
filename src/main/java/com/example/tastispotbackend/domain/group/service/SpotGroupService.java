package com.example.tastispotbackend.domain.group.service;

import com.example.tastispotbackend.domain.group.component.InvitationCodeGenerator;
import com.example.tastispotbackend.domain.group.dto.request.GroupAddRequest;
import com.example.tastispotbackend.domain.group.mapper.GroupMapper;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import com.example.tastispotbackend.global.entity.SpotGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpotGroupService {
    private final GroupMapper groupMapper;

    @Transactional
    public void addGroup(SimpleUserAuth simpleUserAuth, GroupAddRequest request) {
        String userId = simpleUserAuth.userId();

        // [1] 유니크한 초대 코드 생성
        String invitationCode = "";

        do {
            invitationCode = InvitationCodeGenerator.generateUniqueCode();
        } while (groupMapper.selectInvitationCodeChk(invitationCode) >= 1);

        SpotGroup spotGroup = new SpotGroup(userId, invitationCode, request);
        groupMapper.insertSpotGroup(spotGroup);
    }
}
