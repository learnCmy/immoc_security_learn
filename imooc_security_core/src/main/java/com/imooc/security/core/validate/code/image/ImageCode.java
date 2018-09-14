package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Auther: cmy
 * @Date: 2018/7/11 16:34
 * @Description:
 */
public class ImageCode  extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }



}
