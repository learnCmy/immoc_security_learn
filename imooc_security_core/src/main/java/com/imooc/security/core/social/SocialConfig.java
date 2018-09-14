package com.imooc.security.core.social;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 16:06
 * @Description:
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //1.先去去JdbcUsersConnectionRepository.sql文件拿到SQL语句，创建数据库表
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());//Encryptors.noOpText()这里先不做加解密。也可使用加密
        jdbcUsersConnectionRepository.setTablePrefix("imooc_");

        System.out.println("connectionSignUp是空吗？？"+connectionSignUp==null);

        if (connectionSignUp!=null){
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;

    }

    @Bean
    public SpringSocialConfigurer imoocSocialSecurityConfig(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        imoocSpringSocialConfigurer configurer=new imoocSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return  configurer;

    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return  new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }

}
