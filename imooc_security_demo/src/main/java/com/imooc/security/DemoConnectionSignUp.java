package com.imooc.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Auther: cmy
 * @Date: 2018/9/5 09:42
 * @Description:
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息 默认创建用户并返回用户唯一标识
        System.out.println("默认创建用户并返回用户唯一标识");
        return connection.getDisplayName();
    }
}
