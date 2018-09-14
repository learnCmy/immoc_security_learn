package com.imooc.security.browser.authentication;

import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: cmy
 * @Date: 2018/7/10 09:21
 * @Description:
 */
@Component("imoocAuthenticationSuccessHandler")          //这个是Spring默认的成功处理器，可以跳转到缓存起来的请求的处理器
public class ImoocAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler{//implements AuthenticationSuccessHandler {
    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 当登录成功会调用
     * @param request
     * @param response
     * @param authentication//该接口封装认证信息(比如认证请求的Ip,session,以及认证通过的UserDetails)
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
