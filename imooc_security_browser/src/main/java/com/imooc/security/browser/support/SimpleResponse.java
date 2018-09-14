package com.imooc.security.browser.support;

/**
 * @Auther: cmy
 * @Date: 2018/7/9 21:40
 * @Description:
 */
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
