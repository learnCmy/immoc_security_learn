package com.imooc.controller;

import com.imooc.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //用来处理其他Controller抛出来的异常
public class ControllerExceptionHandle {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回的http状态码
    public Map<String ,Object> handleUserNotExist(UserNotExistException ex){
       Map<String,Object> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("id",ex.getId());
        return map;

    }
}
