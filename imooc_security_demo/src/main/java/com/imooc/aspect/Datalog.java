package com.imooc.aspect;

import java.lang.annotation.*;

/**
 * @Auther: cmy
 * @Date: 2018/11/16 15:57
 * @Description:
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Datalog {

    String name();
}
