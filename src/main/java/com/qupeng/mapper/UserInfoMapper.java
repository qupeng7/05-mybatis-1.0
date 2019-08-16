package com.qupeng.mapper;


import com.qupeng.model.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

}