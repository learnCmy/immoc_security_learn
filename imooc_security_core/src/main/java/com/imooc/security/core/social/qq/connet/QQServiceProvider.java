package com.imooc.security.core.social.qq.connet;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 14:53
 * @Description:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private  static  final String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";
    private static  final  String URL_ACCESSTOKEN="https://graph.qq.com/oauth2.0/token";

    /** clientId，clientSecret即appId和appKey
     */
    public QQServiceProvider(String clientId, String clientSecret) {
        //super(new OAuth2Template(clientId,clientSecret ,URL_AUTHORIZE, URL_ACCESSTOKEN));
        super(new QQOAuth2Template(clientId,clientSecret,URL_AUTHORIZE,URL_ACCESSTOKEN));
        this.appId=clientId;

    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
