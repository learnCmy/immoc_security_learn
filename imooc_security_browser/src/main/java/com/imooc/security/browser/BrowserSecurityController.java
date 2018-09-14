package com.imooc.security.browser;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.browser.support.SocialUserInfo;
import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: cmy
 * @Date: 2018/7/9 21:26
 * @Description:
 */
@RestController
public class BrowserSecurityController {
    private Logger logger=LoggerFactory.getLogger(getClass());

    private RequestCache requestCache=new HttpSessionRequestCache(); //在引发跳转之前 springSecurity将引发的跳转的请求，缓存到esssion。

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code =  HttpStatus.UNAUTHORIZED)//未授权的状态码
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);//拿到引发跳转的请求
        if(savedRequest!=null){
            String targetUrl=savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求时："+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());//做跳转
            }
        }
        return new SimpleResponse("访问的服务需要身份验证，请引导用户到登录页");
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){

        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setNickname(connection.getDisplayName());
        socialUserInfo.setHeadimg(connection.getImageUrl());

        return socialUserInfo;
    }
}
