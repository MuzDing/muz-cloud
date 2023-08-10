package com.muz.cn.serivce;

import com.muz.cn.pojo.po.SysFarmPlayerLevel;
import com.muz.cn.pojo.po.SysFarmShop;
import com.muz.cn.repository.SysFarmPlayerLevelRepository;
import com.muz.cn.repository.SysFarmShopRepository;
import jakarta.annotation.PostConstruct;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.security.Security;
import java.util.List;

@Configuration
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

        Security.addProvider(new BouncyCastleProvider());
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
        if (sysFarmPlayerLevels == null) {
            return sysFarmPlayerLevelRepository.findAll();
        } else {
            return sysFarmPlayerLevels;
        }

    }

}
