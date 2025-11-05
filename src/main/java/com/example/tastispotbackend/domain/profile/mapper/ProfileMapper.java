package com.example.tastispotbackend.domain.profile.mapper;

import com.example.tastispotbackend.domain.profile.dto.response.ProfileResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper {
    ProfileResponse selectUserById(String userId);
}
