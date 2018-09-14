package com.imooc.security.browser.support;

/**
 * @Auther: cmy
 * @Date: 2018/9/2 21:49
 * @Description:
 */
public class SocialUserInfo {
    /**
     * 告诉前台是哪个第三方的应用在做社交登录
     */
    private String providerId;

    /**
     * 用户OpenId
     */
    private String providerUserId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headimg;


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
