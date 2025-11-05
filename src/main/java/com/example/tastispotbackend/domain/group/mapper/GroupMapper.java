package com.example.tastispotbackend.domain.group.mapper;

import com.example.tastispotbackend.domain.group.dto.response.GroupResponse;
import com.example.tastispotbackend.global.entity.Group;
import com.example.tastispotbackend.global.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<GroupResponse> selectGroupList(String userId);
    
    int selectInvitationCodeChk(String invitationCode);

    void insertGroup(Group group);

    void insertGroupMember(GroupMember groupMember);
}
