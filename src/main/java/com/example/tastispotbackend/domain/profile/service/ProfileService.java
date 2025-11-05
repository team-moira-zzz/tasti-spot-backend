package com.example.tastispotbackend.domain.profile.service;

import com.example.tastispotbackend.domain.profile.dto.response.ProfileResponse;
import com.example.tastispotbackend.domain.profile.mapper.ProfileMapper;
import com.example.tastispotbackend.global.auth.SimpleUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileMapper profileMapper;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();

        return profileMapper.selectUserById(userId);
    }
}
