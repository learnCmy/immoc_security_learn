package com.imooc.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Component
public class TimerInterceptor implements HandlerInterceptor {
    /**
     *Interceptor可以获取http请求和响应，能够真正处理请求方法的信息，但是不能拿到真正调用时参数的值
     */

    @Override//在Contorller方法调用之前执行
    /**
     * 在preHandle中可以进行编码，安全控制等处理
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        request.setAttribute("startTime",new Date().getTime());
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        return true;
    }

    @Override
    //在 controller方法被调用之后会被调用(如果controller层抛出了异常，这个方法就不会被调用)
    /**
     * 在postHandle中，有机会修改ModelAndView
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-(Long)request.getAttribute("startTime")));
    }

    @Override//此方法不管有没有抛出异常都会被调用
    /**
     * 可以根据Ex是否为null判断是否发生了异常
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-(Long)request.getAttribute("startTime")));
        System.out.println("ex is "+ex);
    }
}
