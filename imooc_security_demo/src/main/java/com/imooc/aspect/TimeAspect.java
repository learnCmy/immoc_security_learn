package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: cmy
 * @Date: 2018/7/5 15:36
 * @Description:
 */
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.imooc.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        long start=new Date().getTime();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        Object proceed = pjp.proceed();
        System.out.println("time aspect 耗時"+(new Date().getTime()-start));

        System.out.println("time aspect end");
        return  proceed;
    }
}
