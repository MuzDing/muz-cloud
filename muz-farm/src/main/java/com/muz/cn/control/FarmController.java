package com.muz.cn.control;

import com.muz.cn.pojo.baseEnum.FarmOperateEnum;
import com.muz.cn.pojo.dto.BaseDataDto;
import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.pojo.dto.PlantGoodsDto;
import com.muz.cn.pojo.dto.SellGoodsDto;
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
    public Object serivce(@RequestBody BaseDataDto dto) throws IllegalAccessException, InstantiationException {
        FarmOperateEnum farmOperate = FarmOperateEnum.getFarmOperateEnum(dto.getOptCode());

        switch (farmOperate) {
            case BUY_GOODS:
                //购买商品
                baseService.buyFarmGoods(BaseUtils.convertToClass(dto.getData(), BuyGoodsDto.class));
                break;
            case FIND_ALL_LANDS:
                //查询所有土地
                return baseService.findAllLands();
            case SELL_GOODS:
                //出售商品
                baseService.sellGoods(BaseUtils.convertToClass(dto.getData(), SellGoodsDto.class));
                break;
            case PLANT_GOODS:
                //种植商品
                baseService.plantGoods(BaseUtils.convertToClass(dto.getData(), PlantGoodsDto.class));
                break;
            case FIND_FARM_PALYER_INFO:
                //获取农场玩家信息
                return baseService.findFarmPlayerInfo();
            case FIND_WAREHOUSE:
                //获取玩家仓库信息
                return baseService.findWarehouse();
            case HARVEST_GOODS:
                //收获作物
                baseService.harvestGoods();
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