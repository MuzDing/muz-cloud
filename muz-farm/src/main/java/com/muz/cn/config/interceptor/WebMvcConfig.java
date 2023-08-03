package com.muz.cn.config.interceptor;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private GloBalInterceptor gloBalInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gloBalInterceptor).addPathPatterns("/**"); // 添加自定义拦截器并设置拦截路径
    }
}
