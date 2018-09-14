package com.imooc.security.core.properties;

/**
 * @Auther: cmy
 * @Date: 2018/7/13 15:14
 * @Description:
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties(){
        setLength(4);
    }

    private int width=67;
    private int height=23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
