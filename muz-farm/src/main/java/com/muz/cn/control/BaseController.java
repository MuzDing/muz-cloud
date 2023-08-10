package com.muz.cn.control;

import com.muz.cn.serivce.BaseService;
import com.muz.cn.util.BouncyCastleUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping(value = "/base",name = "基本操作")
public class BaseController {
    @Autowired
    private BaseService baseService;

    @GetMapping (value = "test")
    public String serivce() throws Exception {
        byte[] encrypt = BouncyCastleUtils.encrypt("123".getBytes());
        System.out.println(new String(encrypt));
        byte[] encrypt1 = BouncyCastleUtils.decrypt(encrypt);
        System.out.println(new String(encrypt1));
        return "success";
    }


}