package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: cmy
 * @Date: 2018/7/13 16:44
 * @Description:
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
