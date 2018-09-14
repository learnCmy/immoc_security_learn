package com.imooc.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

/**
 * @Auther: cmy
 * @Date: 2018/7/23 10:35
 * @Description:
 */
//@Component
@Slf4j

public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new Thread(()->{
            while (true){
            log.info("{}","启动1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}
        }).start();

    }
}
