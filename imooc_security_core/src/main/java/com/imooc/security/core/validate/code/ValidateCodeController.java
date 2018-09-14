package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Auther: cmy
 * @Date: 2018/7/11 16:38
 * @Description:
 */
@RestController
public class ValidateCodeController {


    @Autowired
    private Map<String ,ValidateCodeProcessor> validateCodeProcessors;


    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception{
        System.out.println(request.getRequestURI());
        validateCodeProcessors.get(type+"ValidateCodeProcessor").create(new ServletWebRequest(request,response));
    }





  /*
    public  static final String SESSION_KEY="SESSION_KEY_IMGAGE_CODE";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator SmsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;
*/

/*    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode= (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }


    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCode smsCode=SmsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        String mobile=ServletRequestUtils.getRequiredStringParameter(request,"mobile");//请求里必须包含这个参数
        smsCodeSender.send(mobile,smsCode.getCode());
    }*/




}
