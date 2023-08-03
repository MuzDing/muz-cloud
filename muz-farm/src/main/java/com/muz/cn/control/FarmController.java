package com.muz.cn.control;

import com.muz.cn.pojo.baseEnum.FarmOperateEnum;
import com.muz.cn.pojo.dto.BaseDataDto;
import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.pojo.dto.PlantGoodsDto;
import com.muz.cn.serivce.BaseService;
import com.muz.cn.util.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "/farm",name = "农场基本操作")
public class FarmController {
    @Autowired
    private BaseService baseService;

    @PostMapping(value = "serivce")
    public String serivce(@RequestBody BaseDataDto dto) throws IllegalAccessException, InstantiationException {
        FarmOperateEnum farmOperate = FarmOperateEnum.getFarmOperateEnum(dto.getOptCode());

        switch (farmOperate) {
            case BUY_GOODS:
                //购买商品
                baseService.buyFarmGoods(BaseUtils.convertToClass(dto.getData(), BuyGoodsDto.class));
                break;

            case FIND_ALL_LANDS:
                //查询所有土地
                baseService.findAllLands(dto);
                break;
            case SELL_GOODS:
                //出售商品
                baseService.sellGoods(dto);
                break;
            case PLANT_GOODS:
                //种植商品
                baseService.plantGoods(BaseUtils.convertToClass(dto.getData(), PlantGoodsDto.class));
                break;


        }
        return "success";
    }
    @GetMapping(value = "/test")
    public String serivce() {
        return "success";
    }
    @GetMapping(value = "/test1")
    public Long serivce1() {
        return 1l;
    }
}