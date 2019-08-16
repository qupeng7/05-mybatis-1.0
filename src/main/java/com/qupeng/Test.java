package com.qupeng;


import com.qupeng.mapper.OrderInfoMapper;
import com.qupeng.mapper.UserInfoMapper;
import com.qupeng.model.UserInfo;
import com.qupeng.mybatis.io.MyResources;
import com.qupeng.mybatis.session.MySqlSession;
import com.qupeng.mybatis.session.MySqlSessionFactory;
import com.qupeng.mybatis.session.MySqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) {

        //第一步：读取mybatis-config.xml配置文件
        InputStream inputStream = MyResources.getResourceAsStream("mybatis-config.xml");

        //第二步：构建SqlSessionFactory (框架初始化)
        MySqlSessionFactory mySqlSessionFactory = new MySqlSessionFactoryBuilder().build(inputStream);

        //第三步：打开SqlSession
        MySqlSession mySqlSession = mySqlSessionFactory.openMySqlSession();

//        //第四步：获取Mapper接口对象 (底层是动态代理)
        UserInfoMapper userInfoMapper = mySqlSession.getMapper(UserInfoMapper.class);
        OrderInfoMapper orderInfoMapper = mySqlSession.getMapper(OrderInfoMapper.class);

//        //第五步：调用Mapper接口对象的方法操作数据库；
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(2);
        System.out.println(userInfo.getId() + "---" + userInfo.getPhone());
        int i=10;

//
//        //第五步：调用Mapper接口对象的方法操作数据库；
//        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(9);
//        System.out.println(userInfo.getId() + "---" + userInfo.getPhone());

    }
}
