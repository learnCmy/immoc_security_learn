package com.imooc.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 17:05
 * @Description:
 */
public class QQProperties extends SocialProperties {

    private String providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
