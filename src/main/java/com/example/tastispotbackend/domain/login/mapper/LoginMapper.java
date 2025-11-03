package com.example.tastispotbackend.domain.login.mapper;

import com.example.tastispotbackend.global.entity.User;
import com.example.tastispotbackend.global.entity.UserLoginHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    int selectEmailChk(String email);

    User selectUserByEmail(String email);

    void insertUserLoginHistory(UserLoginHistory userLoginHistory);
}
