package com.imooc.security.core.validate.code.sms;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: cmy
 * @Date: 2018/7/26 14:46
 * @Description:
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;


    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile=ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");//请求里必须包含这个参数
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
