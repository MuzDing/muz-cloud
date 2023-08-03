package com.muz.cn.control;

import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.serivce.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping(value = "/base",name = "农场基本操作")
public class BaseController {
    @Autowired
    private BaseService baseService;

    @PostMapping(value = "serivce")
    public String serivce(@RequestBody BuyGoodsDto dto){
        baseService.handleService(dto);
        return "success";
    }
}
