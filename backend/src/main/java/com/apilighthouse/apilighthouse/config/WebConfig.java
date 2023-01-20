package com.apilighthouse.apilighthouse.config;


import org.aopalliance.intercept.Interceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@Component
public class WebConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor( new LoginInterceptor()).addPathPatterns("/adimin/**");


    }

    //加载静态页面的css和js文件
    //参考链接：https://blog.51cto.com/u_15236724/5369362
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}