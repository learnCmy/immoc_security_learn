
package com.imooc.security.browser;


import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Auther: cmy
 * @Date: 2018/7/6 17:26
 * @Description:
*/
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

      /*  ValidateCodeFilter2 validateCodeFilter= new ValidateCodeFilter2();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();


        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();*/

      applyPasswordAuthenticationConfig(http);
      http.apply(smsCodeAuthenticationSecurityConfig)
              .and().apply(validateCodeSecurityConfig)
              .and().apply(imoocSocialSecurityConfig)//相当于加个过滤器，会拦截特定的请求
              .and().rememberMe().tokenRepository(persistentTokenRepository())
              .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
              .userDetailsService(userDetailsService)
              .and().authorizeRequests()//对请求做一个授权
              .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                      SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                      securityProperties.getBrowser().getLoginPage(),
                       SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                      "/user/regist")
              .permitAll()//当访问到这个Url时不需要身份认证
              .anyRequest()//对任何请求
              .authenticated()//都需要身份认证
              .and()
              .csrf().disable();//关闭跨站防护的功能

        //http.httpBasic().

       /* http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
               .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)//把自定义的过滤器加到UsernamePasswordAuthenticationFilter之前
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")//当这个请求时使用UsernamePasswordAuthenticationFilter来处理这个请求
                .successHandler(imoocAuthenticationSuccessHandler)//表当登录后用自定义的handler处理
                .failureHandler(imoocAuthenticationFailureHandler)
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and().authorizeRequests()//对请求做一个授权
                .antMatchers("/authentication/require","/authentication/mobile",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()//当访问到这个Url时不需要身份认证
                .anyRequest()//对任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable()//关闭跨站防护的功能
                .apply(smsCodeAuthenticationSecurityConfig);*/
    }
}

