package com.muz.cn.serivce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muz.cn.config.LoginUserContext;
import com.muz.cn.pojo.baseEnum.GoodsTypeEnum;
import com.muz.cn.pojo.baseEnum.LockEnum;
import com.muz.cn.pojo.bo.GoodsInfo;
import com.muz.cn.pojo.bo.WeatherResponse;
import com.muz.cn.pojo.dto.BuyGoodsDto;
import com.muz.cn.pojo.dto.PlantGoodsDto;
import com.muz.cn.pojo.dto.SellGoodsDto;
import com.muz.cn.pojo.po.SysFarmPlayer;
import com.muz.cn.pojo.po.SysFarmPlayerLevel;
import com.muz.cn.pojo.po.SysFarmPlayerWarehouse;
import com.muz.cn.pojo.po.SysFarmShop;
import com.muz.cn.repository.SysFarmPalyerRepository;
import com.muz.cn.repository.SysFarmPalyerWarehouseRepository;
import com.muz.cn.util.BaseUtils;
import com.muz.framework.utils.IdUtils;
import jakarta.servlet.http.HttpServletRequest;
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
    private LoginUserContext loginUser;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SysFarmPalyerRepository sysFarmPalyerRepository;
    @Autowired
    private SysFarmPalyerWarehouseRepository SysFarmPalyerWarehouseRepository;
    @Autowired
    private IdUtils idUtils;
    @Autowired
    private AppInstance appInstance;
    @Autowired
    private WeatherService weatherUtils;

    /**
     * 购买商品
     * @param dto
     */
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
            updateWarehouse(dto.getGoodsId(), dto.getGoodsNum(), dto.getGoodsType(),sysFarmPlayerWarehouse);
            palyer.setMoney(newMoney);
            sysFarmPalyerRepository.save(palyer);

        }
    }

    /**
     * 查询土地信息
     * @return
     */
    public Object findAllLands(HttpServletRequest request) {
        long userId = loginUser.getUserId();
        // 先获取该玩家的地理信息,进行土地信息的更新
        Object weatherObj = redisTemplate.opsForValue().get(loginUser.getCity() + "Weather");
//        Object landsObj = redisTemplate.opsForHash().get(String.valueOf(userId), loginUser.getUserId() + "_lands");
        List lands = (List)redisTemplate.opsForHash().get(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands");
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weather = objectMapper.convertValue(weatherObj, WeatherResponse.class);
        if (lands == null){
            int playerLevel = sysFarmPalyerRepository.findByUserId(userId).getPlayerLevel();
            List<SysFarmPlayerLevel> sysFarmPlayerLevelList = appInstance.getSysFarmPlayerLevel();
            Integer landNumber = sysFarmPlayerLevelList.stream().filter(e -> e.getLevel().equals(playerLevel)).findFirst().get().getLandNumber();
            lands = new ArrayList(landNumber);
            redisTemplate.opsForHash().put(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands", lands);
        }else if(weather != null){
            for (int index = 0; index < lands.size(); index++) {
                if (lands.get(index) != null) {
                    // 1.地块 2.种植id 3.种植时间 4.成长时间 5.是否受天气影响 6.成熟次数
                    List land = (List) lands.get(index);
                    boolean isEffect = (boolean) land.get(5);
                    if (!isEffect){
                        long growthTime = (long) land.get(4);
                        long newGrowthTime = (long) (growthTime *0.8);
                        land.set(4, newGrowthTime);
                    }
                    lands.set(index,land);

                }
            }
        }
        return lands;
    }

    /**
     * 出售商品
     * @param dto
     */
    public void sellGoods(SellGoodsDto dto) {
        List<SysFarmShop> sysFarmShop = appInstance.getSysFarmShop();

        SysFarmPlayer player = sysFarmPalyerRepository.findByUserId(loginUser.getUserId());
        SysFarmPlayerWarehouse farmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());
        int money = 0;
        for (GoodsInfo goodsInfo : dto.getGoodsList()) {
            if (goodsInfo.getLock().equals(LockEnum.LOCK.getCode())){
                continue;
            }
            SysFarmShop oneGoodsInfo = sysFarmShop.stream().filter(e -> e.getGoodsId().equals(goodsInfo.getGoodsId())).findFirst().get();
            money += oneGoodsInfo.getMaturityPrice() * goodsInfo.getNumber();
        }
        List<GoodsInfo> goodsList = dto.getGoodsList();
        goodsList.stream()
                .filter(e -> e.getLock() == 0)
                .forEach(e -> e.setNumber(-e.getNumber()));

        updateWarehouse(goodsList,farmPlayerWarehouse);
        player.setMoney(player.getMoney() + money);

    }

    /**
     * 种地
     * @param dto
     */

    public void plantGoods(PlantGoodsDto dto) {
        Long userId = loginUser.getUserId();

        List<SysFarmPlayerLevel> sysFarmPlayerLevelList = appInstance.getSysFarmPlayerLevel();
        List<SysFarmShop> sysFarmShopList = appInstance.getSysFarmShop();
        SysFarmPlayerWarehouse sysFarmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());

        Integer playerLevel = sysFarmPalyerRepository.findByUserId(userId).getPlayerLevel();
        Integer landNumber = sysFarmPlayerLevelList.stream().filter(e -> e.getLevel().equals(playerLevel)).findFirst().get().getLandNumber();
        SysFarmShop sysFarmShop = sysFarmShopList.stream().filter(e -> e.getGoodsId().equals(dto.getGoodsId())).findFirst().get();
        int warehouseNum = sysFarmPlayerWarehouse.getGoodsList().stream().filter(e ->
                e.getGoodsId().equals(dto.getGoodsId()) && e.getType().equals(dto.getGoodsType())).findFirst().get().getNumber();

        List lands = (List)redisTemplate.opsForHash().get(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands");
        int plantNum = dto.getGoodsNum();
        for(int index = 0; index < landNumber ; index++){
            if (lands.get(index) == null ){
                // 1.地块 2.种植id 3.种植时间 4.成长时间 5.是否受天气影响 6.成熟次数
                if (plantNum != 0 && plantNum < warehouseNum){
                    List land = new ArrayList();
                    land.add(index + 1);
                    land.add(dto.getGoodsId());
                    land.add(Instant.now().plusSeconds(BaseUtils.Double2Seconds(sysFarmShop.getMaturityTime())).toEpochMilli());
                    land.add(BaseUtils.Double2Seconds(sysFarmShop.getMaturityTime())*1000);
                    land.add(false);
                    land.add(0);
                    lands.set(index,land);
                }
                plantNum--;
                warehouseNum--;
                if(plantNum ==0 || warehouseNum == 0){
                    break;
                }
            }
        }

        updateWarehouse(dto.getGoodsId(), -(dto.getGoodsNum() - plantNum),dto.getGoodsType(),sysFarmPlayerWarehouse);
        redisTemplate.opsForHash().put(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands", lands);
    }

    public SysFarmPlayer findFarmPlayerInfo() {
        SysFarmPlayer farmPlayer = sysFarmPalyerRepository.findByUserId(loginUser.getUserId());
        return farmPlayer;
    }

    public SysFarmPlayerWarehouse findWarehouse() {
        SysFarmPlayerWarehouse farmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());
        return farmPlayerWarehouse;
    }

    /**
     * 对仓库在的商品进行一次新的计算
     * @return
     */
    private void updateWarehouse(int goodsId,int number,int type,SysFarmPlayerWarehouse farmPlayerWarehouse) {
        List<GoodsInfo> goodsList = farmPlayerWarehouse.getGoodsList();
        Optional<GoodsInfo> foundGoods = goodsList.stream()
                .filter(goodsInfo ->
                        goodsInfo.getGoodsId().equals(goodsId) && goodsInfo.getType().equals(type))
                .findFirst();

        if (foundGoods.isPresent()) {
            GoodsInfo goodsInfo = foundGoods.get();
            goodsInfo.setNumber(goodsInfo.getNumber() + number);
        } else {
            goodsList.add(GoodsInfo.builder()
                    .goodsId(goodsId)
                    .number(number)
                    .type(foundGoods.get().getType())
                    .lock(LockEnum.NOT_LOCK.getCode())
                    .build());
        }
        farmPlayerWarehouse.setGoodsList(goodsList);
        SysFarmPalyerWarehouseRepository.save(farmPlayerWarehouse);
    }

    private void updateWarehouse(List<GoodsInfo> partGoodsInfoList, SysFarmPlayerWarehouse farmPlayerWarehouse) {
        List<GoodsInfo> goodsList = farmPlayerWarehouse.getGoodsList();
        for(GoodsInfo goodsInfo:partGoodsInfoList){
            goodsList.stream()
                    .filter(e-> e.getGoodsId().equals(goodsInfo.getGoodsId()) && e.getType().equals(goodsInfo.getType()))
                    .findFirst()
                    .ifPresentOrElse(
                            existGoodsInfo -> existGoodsInfo.setNumber(existGoodsInfo.getNumber() + goodsInfo.getNumber()),
                            () -> goodsList.add(goodsInfo)
                    );
        }
        farmPlayerWarehouse.setGoodsList(goodsList);
        SysFarmPalyerWarehouseRepository.save(farmPlayerWarehouse);
    }

    public void harvestGoods() {
        SysFarmPlayer player = sysFarmPalyerRepository.findByUserId(loginUser.getUserId());
        List<SysFarmPlayerLevel> sysFarmPlayerLevelList = appInstance.getSysFarmPlayerLevel();
        SysFarmPlayerWarehouse sysFarmPlayerWarehouse = SysFarmPalyerWarehouseRepository.findByUserId(loginUser.getUserId());

        List<SysFarmShop> farmShopList = appInstance.getSysFarmShop();
        long now = Instant.now().toEpochMilli();
        List<List> lands = (List) redisTemplate.opsForHash().get(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands");

        // 记录收益的经验
        int exp = 0;
        // 记录收获的物品
        List<GoodsInfo> goodsList = new ArrayList<>();
        for (int index = 0; index < lands.size(); index++) {
            if (lands.get(index) != null) {
                // 1.地块id 2.种植id 3.种植时间 4.成长时间 5.是否受天气影响 6.成熟次数
                List land = lands.get(index);
                long maturityTime = (long) land.get(2);
                if (now > maturityTime) {
                    Integer goodsId = (Integer) land.get(1);
                    Integer maturityTimes = (Integer) land.get(3);
                    SysFarmShop goods = farmShopList.stream().filter(e -> e.getGoodsId().equals(goodsId)).findFirst().get();
                    int goodsMaturityTimes = goods.getMaturityTimes();
                    if (maturityTimes + 1 == goodsMaturityTimes) {
                        land = null;
                    } else {
                        land.set(3, now );
                        land.set(4, BaseUtils.Double2Seconds(goods.getMaturityTime())*1000);
                        land.set(5,false);
                        land.set(6, maturityTimes + 1);

                    }
                    exp += goods.getMaturityExp();
                    goodsList.stream()
                            .filter(e -> e.getGoodsId().equals(goods.getGoodsId()))
                            .findFirst()
                            .ifPresentOrElse(e -> e.setNumber(e.getNumber() + goods.getMaturityNumber()),
                                    () -> { goodsList.add(GoodsInfo.builder()
                                            .goodsId(goodsId)
                                            .type(GoodsTypeEnum.PLANT_FRUIT.getCode())
                                            .number(goods.getMaturityNumber())
                                            .lock(LockEnum.NOT_LOCK.getCode())
                                            .build());
                                    });

                    lands.set(index,land);
                }
            }
        }
        updateWarehouse(goodsList, sysFarmPlayerWarehouse);
        SysFarmPlayerLevel sysFarmPlayerLevel = sysFarmPlayerLevelList.stream().filter(e -> e.getLevel().equals(player.getPlayerLevel())).findFirst().get();

        if (player.getExp() + exp > sysFarmPlayerLevel.getExp()){
            player.setPlayerLevel(player.getPlayerLevel() + 1);
            player.setExp((player.getExp() + exp ) -sysFarmPlayerLevel.getExp());
        }else {
            player.setExp(player.getExp() + exp);
        }
        redisTemplate.opsForHash().put(loginUser.getUserId().toString(), loginUser.getUserId() + "_lands", lands);
        sysFarmPalyerRepository.save(player);

    }
}
