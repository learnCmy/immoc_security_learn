package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: cmy
 * @Date: 2018/7/12 14:12
 * @Description:
 */                                        //这是在登录验证中所遇到异常的基类
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
