package com.imooc.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: cmy
 * @Date: 2018/7/26 09:29
 * @Description:
 */
@Component
@Slf4j
public class CalendarTest {

    @Scheduled(cron = "0 */1 * * * ?")
    public void test1(){
        log.info("定时任务1启动：{}",Thread.currentThread().getName());
    }

}
