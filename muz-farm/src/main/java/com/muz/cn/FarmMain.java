package com.muz.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


//@EnableDiscoveryClient
//@EnableFeignClients
public class FarmMain {
    public static void main(String[] args) {
        SpringApplication.run(FarmMain.class,args);
        System.out.println("service is ok");
    }


}
