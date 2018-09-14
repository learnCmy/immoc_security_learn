package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Auther: cmy
 * @Date: 2018/7/7 17:44
 * @Description:
 */
@Component
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名："+username);
        //根据用户名查找用户名
        return builderUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户Id："+userId);
        return builderUser(userId);
    }

    private SocialUserDetails builderUser(String userId) {
        //根据用户名查找用户名
        //根据查找到的用户信息判断用户是否被冻结
        String password=passwordEncoder.encode("123456");
        logger.info("数据库密码是："+password);
        return new SocialUser(userId,password,
                true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
