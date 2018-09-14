package com.imooc.security.core.properties;

/**
 * @Auther: cmy
 * @Date: 2018/7/13 15:17
 * @Description:
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();

    private SmsCodeProperties sms=new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
