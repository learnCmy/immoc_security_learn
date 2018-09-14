package com.imooc.security.core.properties;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 17:07
 * @Description:
 */
public class SocialProperties {
    private QQProperties qq=new QQProperties();

    private String filterProcessesUrl="/auth";

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
