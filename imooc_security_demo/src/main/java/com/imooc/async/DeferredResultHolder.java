package com.imooc.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cmy
 * @Date: 2018/7/6 10:44
 * @Description:
 */
@Component
public class DeferredResultHolder {
    private Map<String,DeferredResult<String>> map=new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
