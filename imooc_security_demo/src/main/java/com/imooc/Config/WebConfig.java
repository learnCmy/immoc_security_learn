package com.imooc.Config;

import com.imooc.filter.TimeFilter;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //@Autowired
   // private TimerInterceptor timerInterceptor;


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //configurer.registerCallableInterceptors();
        //configurer.registerDeferredResultInterceptors();
        //configurer.setDefaultTimeout(10000);异步请求的超时请求
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(timerInterceptor);
    }

    //@Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urls=new ArrayList<>();
        urls.add("/*");//拦截所有的Url
        filterRegistrationBean.setUrlPatterns(urls);
         return  filterRegistrationBean;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }

}

