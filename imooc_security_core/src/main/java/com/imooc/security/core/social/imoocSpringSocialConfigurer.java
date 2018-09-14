package com.imooc.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Auther: cmy
 * @Date: 2018/8/25 10:53
 * @Description:
 */
public class imoocSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public imoocSpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl=filterProcessesUrl;
    }



    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter filter=(SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)filter;
    }
}
