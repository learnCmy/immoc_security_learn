package com.imooc.security.core.validate.code.impl;

import com.imooc.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Arrays;
import java.util.Map;

/**
 * @Auther: cmy
 * @Date: 2018/7/26 14:07
 * @Description:
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    private C generate(ServletWebRequest request){
        String type = getValidateCodeType(request).toString().toLowerCase();
        String  generatorName=type+ValidateCodeGenerator.class.getSimpleName();
        System.out.println("generatorName:"+generatorName);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        System.out.println("maps:"+validateCodeGenerators.size()+" "+Arrays.toString(validateCodeGenerators.keySet().toArray()));
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }


    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
    }


    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }


    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        System.out.println("!!"+getClass().getSimpleName());
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        System.out.println(type);
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


    @Override
    public void validate(ServletWebRequest servletWebRequest) {
        ValidateCodeType validateCodeType = getValidateCodeType(servletWebRequest);
        String sessionKey=getSessionKey(servletWebRequest);
       C codeInSession= (C) sessionStrategy.getAttribute(servletWebRequest,sessionKey);
       String codeInRequest;
        try {
            codeInRequest= ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw  new ValidateCodeException("获取验证码失败");
        }

        if(StringUtils.isBlank(codeInRequest)){
            throw  new ValidateCodeException("验证码不能为空");
        }
        if(codeInRequest==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
            throw new ValidateCodeException("验证码过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
    }
}
