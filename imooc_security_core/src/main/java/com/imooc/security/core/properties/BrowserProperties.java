package com.imooc.security.core.properties;

/**
 * @Auther: cmy
 * @Date: 2018/7/9 22:26
 * @Description:
 */
public class BrowserProperties {
    private String signUpUrl=SecurityConstants.DEFAULT_SIGNIN_PAGE_URL;

    private String loginPage=SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType=LoginType.JSON;//默认返回一个json

    private int rememberMeSeconds=3600;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {

        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
