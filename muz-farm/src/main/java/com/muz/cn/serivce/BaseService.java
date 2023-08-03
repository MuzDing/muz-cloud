package com.muz.cn.serivce;

import com.muz.cn.config.LoginUser;
import com.muz.cn.pojo.bo.GoodsInfo;
import com.muz.cn.pojo.dto.BaseDataDto;
import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.pojo.dto.PlantGoodsDto;
import com.muz.cn.pojo.po.*;
import com.muz.cn.repository.SysFarmPalyerRepository;
import com.muz.cn.repository.SysFarmPalyerWarehouseRepository;
import com.muz.cn.repository.SysFarmPlayerLevelRepository;
import com.muz.cn.util.AppInstance;
import com.muz.framework.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseService {

    @Autowired
    private LoginUser loginUser;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SysFarmPalyerRepository sysFarmPalyerRepository;
    @Autowired
    private SysFarmPalyerWarehouseRepository SysFarmPalyerWarehouseRepository;
    @Autowired
    private SysFarmPlayerLevelRepository sysFarmPlayerLevelRepository;
    @Autowired
    private IdUtils idUtils;
    @Autowired
    private AppInstance appInstance;

    public void buyFarmGoods(BuyGoodsDto dto){
        SysFarmPlayer palyer = sysFarmPalyerRepository.findByUserId(loginUser.getUserId());
        SysFarmShop goods = appInstance.getSysFarmShop().stream().filter(e -> e.getGoodsId().equals(dto.getGoodsId())).findFirst().get();
        int money = goods.getGoodsPrice() * dto.getGoodsNum();
        int newMoney = palyer.getMoney() - money;

        if (newMoney > 0 ){
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

            palyer.setMoney(newMoney);
            sysFarmPalyerRepository.save(palyer);
            SysFarmPalyerWarehouseRepository.save(sysFarmPlayerWarehouse);
        }
    }

    public void findAllLands(BaseDataDto dto) {
    }

    public void sellGoods(BaseDataDto dto) {
    }

    public void plantGoods(PlantGoodsDto dto) {
        Long userId = loginUser.getUserId();

        List<SysFarmPlayerLevel> sysFarmPlayerLevelList = appInstance.getSysFarmPlayerLevel();
        List<SysFarmShop> sysFarmShopList = appInstance.getSysFarmShop();
        SysFarmPlayerWarehouse sysFarmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());

        Integer playerLevel = sysFarmPalyerRepository.findByUserId(userId).getPlayerLevel();
        Integer landNumber = sysFarmPlayerLevelList.stream().filter(e -> e.getLevel().equals(playerLevel)).findFirst().get().getLandNumber();
        SysFarmShop sysFarmShop = sysFarmShopList.stream().filter(e -> e.getGoodsId().equals(dto.getGoodsId())).findFirst().get();
        int shouldPlantNum = sysFarmPlayerWarehouse.getGoodsList().stream().filter(e -> e.getGoodsId().equals(dto.getGoodsId())).findFirst().get().getNumber();


        Object o = redisTemplate.opsForHash().get(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands");
        List<SysFarmLand> lands = null;
        if (o == null) {
            lands = new ArrayList<>();
        }
        else{
            lands = (List<SysFarmLand>) o;
        }
        for(int i = 0; i < landNumber && i < dto.getGoodsNum() && i < shouldPlantNum; i++){
            lands.add(SysFarmLand.builder()
                    .landId(i)
                    .userId(userId)
                    .goodsId(dto.getGoodsId())
                    .timeStamp(Instant.now().toEpochMilli())
                    .maturity_time(sysFarmShop.getMaturityTime())
                    .maturity_times(0)
                    .build());
        }
        redisTemplate.opsForHash().put(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands", lands);
    }
}
