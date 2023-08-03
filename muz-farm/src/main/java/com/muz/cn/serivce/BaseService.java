package com.muz.cn.serivce;

import com.muz.cn.config.LoginUser;
import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.pojo.po.GoodsInfo;
import com.muz.cn.pojo.po.SysFarmPlayer;
import com.muz.cn.pojo.po.SysFarmPlayerWarehouse;
import com.muz.cn.pojo.po.SysFarmShop;
import com.muz.cn.repository.SysFarmLandRepository;
import com.muz.cn.repository.SysFarmPalyerRepository;
import com.muz.cn.repository.SysFarmPalyerWarehouseRepository;
import com.muz.cn.util.AppInstance;
import com.muz.framework.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseService {

    @Autowired
    private LoginUser loginUser;
    @Autowired
    private SysFarmLandRepository sysFarmLandRepository;
    @Autowired
    private SysFarmPalyerRepository sysFarmPalyerRepository;
    @Autowired
    private SysFarmPalyerWarehouseRepository SysFarmPalyerWarehouseRepository;
    @Autowired
    private IdUtils idUtils;
    @Autowired
    private AppInstance appInstance;

    public void handleService(BuyGoodsDto dto){
        SysFarmPlayer palyer = sysFarmPalyerRepository.findByUserId(loginUser.getUserId());
        SysFarmShop goods = appInstance.getSysFarmShop().stream().filter(e -> e.getGoodsId().equals(dto.getGoodsId())).findFirst().get();
        int money = goods.getGoodsPrice() * dto.getGoodsNum();
        int newMoney = palyer.getMoney() - money;

        SysFarmPlayerWarehouse sysFarmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());
        if (sysFarmPlayerWarehouse == null){
            sysFarmPlayerWarehouse = new SysFarmPlayerWarehouse();
            sysFarmPlayerWarehouse.setId(idUtils.nextId());
            sysFarmPlayerWarehouse.setUserId(loginUser.getUserId());
            sysFarmPlayerWarehouse.setGoodsList(new ArrayList<>());
        }
        List<GoodsInfo> goodsList = sysFarmPlayerWarehouse.getGoodsList();
        Optional<GoodsInfo> foundGoods = goodsList.stream()
                .filter(goodsInfo -> goodsInfo.getGoodsId().equals(dto.getGoodsId()))
                .findFirst();

        if (foundGoods.isPresent()) {
            GoodsInfo goodsInfo = foundGoods.get();
            goodsInfo.setNumber(goodsInfo.getNumber() + dto.getGoodsNum());
        } else {
            goodsList.add(GoodsInfo.builder()
                    .goodsId(dto.getGoodsId())
                    .number(dto.getGoodsNum())
                    .type(1)
                    .lock(0)
                    .build());
        }
        sysFarmPlayerWarehouse.setGoodsList(goodsList);
        if (newMoney > 0 ){
            palyer.setMoney(newMoney);
            sysFarmPalyerRepository.save(palyer);
            SysFarmPalyerWarehouseRepository.save(sysFarmPlayerWarehouse);
        }
    }
}
