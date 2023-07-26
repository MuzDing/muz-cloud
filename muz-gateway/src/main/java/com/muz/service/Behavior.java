package com.muz.service;


import com.muz.config.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "yunyao-behavior-service")
public interface Behavior {

    @PostMapping(value = "/behavior/handle")
    ApiResult writeBehavior(@RequestParam("uid") String uid);

}
