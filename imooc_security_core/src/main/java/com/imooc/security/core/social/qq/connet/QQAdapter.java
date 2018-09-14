package com.imooc.security.core.social.qq.connet;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Auther: cmy
 * @Date: 2018/8/1 15:47
 * @Description:
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;//去做一个测试，是否通。这里不判断了。默认返回true
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo=api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());//用户的名称
        values.setImageUrl(userInfo.getFigureurl_1());//拿头像
        values.setProfileUrl(null);//在qq中是没用的。因为qq是没有什么个人主页的。
        values.setProviderUserId(userInfo.getOpenId());//用户在服务提供商的唯一编号
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {

        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do nothing
    }
}
