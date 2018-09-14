package com.imooc.security.core.validate.code.sms;

/**
 * @Auther: cmy
 * @Date: 2018/7/26 11:08
 * @Description:
 */
public interface SmsCodeSender {
    void  send(String mobile,String code);
}
