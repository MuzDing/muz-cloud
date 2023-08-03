package com.muz.cn.config.bean;

import com.muz.framework.utils.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {


    @Bean
    public IdUtils getIdUtils(){
        return new IdUtils();
    }
}
