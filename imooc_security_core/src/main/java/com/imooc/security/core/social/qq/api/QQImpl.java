package com.imooc.security.core.social.qq.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 13:56
 * @Description:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{


    private Logger logger=LoggerFactory.getLogger(getClass());

    private  static  final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static  final  String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper=new ObjectMapper();

    public QQImpl(String accessToken,String appId) {
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);//选这个策略的时候，用restTemplate发请求的时候他会自动把accessToken当做查询参数挂上去
        this.appId=appId;
        String url=String.format(URL_GET_OPENID,accessToken);
        String result=getRestTemplate().getForObject(url,String.class);
        logger.info("获取OpenID字符串结果"+result);
        this.openId=StringUtils.substringBetween(result,"\"openid\":\"","\"}");

    }

    @Override
    public QQUserInfo getUserInfo() {
        String url=String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("返回的用户信息结果"+result);
        QQUserInfo userInfo=null;
        try {
            //json转bean忽略没有的字段 not marked as ignorable
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            userInfo=objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            throw  new RuntimeException("获取用户信息失败",e);
        }


    }
}
