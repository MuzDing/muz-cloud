package com.muz.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@EnableDiscoveryClient
//@EnableFeignClients
public class FarmMain {
    public static void main(String[] args) {
        SpringApplication.run(FarmMain.class,args);
        System.out.println("service is ok");
    }
}
