package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.imooc.mapper")
@RestController
@EnableSwagger2
@EnableScheduling
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }


}
