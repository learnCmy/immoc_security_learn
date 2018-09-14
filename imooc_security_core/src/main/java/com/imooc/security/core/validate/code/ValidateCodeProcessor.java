package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: cmy
 * @Date: 2018/7/26 14:09
 * @Description: servletWebRequest 是Spring的工具类用来封装请求和响应
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     */

    void create(ServletWebRequest request) throws Exception;
    /**
     * 校验验证码
     */

    void validate(ServletWebRequest servletWebRequest);
}
