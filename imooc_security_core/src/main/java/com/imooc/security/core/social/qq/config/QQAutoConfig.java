package com.imooc.security.core.social.qq.config;

import com.imooc.security.core.properties.QQProperties;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 17:18
 * @Description:
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq",name = "app-id")//当配置Appid的值了。此配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConifg = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConifg.getProviderId(),qqConifg.getAppId(),qqConifg.getAppSecret());
    }
}
