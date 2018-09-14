package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cmy
 * @Date: 2018/7/13 16:49
 * @Description:
 */
@Configuration
public class ValidateBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")//在初始化容器时，会先找下。
                                                        // 是否已经有一个bean，如果能找到就不会用此bean
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator=new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return  imageCodeGenerator;

    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsSender(){
        return  new DefaultSmsCodeSender();
    }
}
