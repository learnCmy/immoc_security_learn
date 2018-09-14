package com.imooc.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Auther: cmy
 * @Date: 2018/7/6 10:24
 * @Description:
 */
@RestController
public class AsyncController {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");
        String orderNumber=RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result=new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);


    /*    Callable<String> result=new Callable<String>() { //想当于单开一个线程
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };*/
        logger.info("主线程结束");
        return result;

    }


    @RequestMapping("/order/1")
    public Callable<String> order1(String aa) throws InterruptedException {
        logger.info("主线程开始");
        Callable<String> result=new Callable<String>() { //想当于单开一个线程
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程结束");
        return result;

    }

}
