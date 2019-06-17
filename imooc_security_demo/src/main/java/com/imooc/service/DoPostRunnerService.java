package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: cmy
 * @Date: 2019/5/23 10:05
 * @Description:
 */
@Component
@Slf4j
public class DoPostRunnerService {


    @Async
    public void sayhello(String name){
        log.info(name);
    }


}
