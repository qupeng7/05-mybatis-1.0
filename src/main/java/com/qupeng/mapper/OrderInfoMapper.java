package com.qupeng.mapper;


import com.qupeng.model.OrderInfo;

public interface OrderInfoMapper {

    OrderInfo selectByPrimaryKey(Integer id);
}