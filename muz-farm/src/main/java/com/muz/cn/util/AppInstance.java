package com.muz.cn.util;

import com.muz.cn.pojo.po.SysFarmPlayerLevel;
import com.muz.cn.pojo.po.SysFarmShop;
import com.muz.cn.repository.SysFarmPlayerLevelRepository;
import com.muz.cn.repository.SysFarmShopRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppInstance {
    private List<SysFarmShop> sysFarmShopList;
    private List<SysFarmPlayerLevel> sysFarmPlayerLevels;

    @Autowired
    private SysFarmShopRepository sysFarmShopRepository;
    @Autowired
    private SysFarmPlayerLevelRepository sysFarmPlayerLevelRepository;

    @PostConstruct
    public void init() {
        sysFarmShopList = getSysFarmShop();
        sysFarmPlayerLevels = getSysFarmPlayerLevel();
    }

    // 示例代码，从数据库中获取数据的方法
    public List<SysFarmShop> getSysFarmShop() {
        if(sysFarmShopList == null){
            return sysFarmShopRepository.findAll();
        }else {
            return sysFarmShopList;
        }
    }

    public List<SysFarmPlayerLevel> getSysFarmPlayerLevel() {
        if(sysFarmPlayerLevels == null){
            return sysFarmPlayerLevelRepository.findAll();
        }else {
            return sysFarmPlayerLevels;
        }

    }

}
