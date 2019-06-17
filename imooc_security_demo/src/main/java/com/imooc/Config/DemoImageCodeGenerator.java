package com.imooc.Config;

import com.imooc.security.core.validate.code.image.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: cmy
 * @Date: 2018/7/13 16:57
 * @Description:
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证生成代码");
        return null;
    }
}
