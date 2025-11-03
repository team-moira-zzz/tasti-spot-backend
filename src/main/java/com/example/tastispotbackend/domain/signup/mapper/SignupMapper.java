package com.example.tastispotbackend.domain.signup.mapper;

import com.example.tastispotbackend.global.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int selectNicknameChk(String nickname);

    int selectEmailChk(String email);

    void insertUser(User user);
}
