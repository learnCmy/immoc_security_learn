package com.imooc.controller;

import com.imooc.service.DoPostRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cmy
 * @Date: 2019/5/23 10:08
 * @Description:
 */
@RestController
public class AsyncTestController {


    @Autowired
    private DoPostRunnerService doPostRunnerService;


    @RequestMapping("async")
    public  String aa(){
        System.out.println("主业务开始");

        int j=0;
        int i1 = j / 0;
        for (int i = 0; i < 10; i++) {
             doPostRunnerService.sayhello("菜菜 ："+i);
        }

        System.out.println("主业务结束");
        return  "ok";
    }


}
