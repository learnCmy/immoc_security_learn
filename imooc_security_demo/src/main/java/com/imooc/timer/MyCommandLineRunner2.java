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
public class MyCommandLineRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        while (true) {
            log.info("{}", "启动2");
            Thread.sleep(1000);
        }
    }
}
