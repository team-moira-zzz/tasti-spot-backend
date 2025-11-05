package com.example.tastispotbackend.domain.group.mapper;

import com.example.tastispotbackend.global.entity.SpotGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {
    int selectInvitationCodeChk(String invitationCode);

    void insertSpotGroup(SpotGroup spotGroup);
}
