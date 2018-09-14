package com.imooc.security.core;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cmy
 * @Date: 2018/7/9 22:29
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)//让SecurityProperties配置类生效
public class SecurityCoreConfig {

}
