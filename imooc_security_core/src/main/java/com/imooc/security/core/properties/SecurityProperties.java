package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: cmy
 * @Date: 2018/7/9 22:25
 * @Description:
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    private BrowserProperties browser=new BrowserProperties();
    private ValidateCodeProperties code=new ValidateCodeProperties();

    private SocialProperties social=new SocialProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }


    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
