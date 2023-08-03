package com.muz.cn.repository;

import com.muz.cn.pojo.po.SysFarmPlayerWarehouse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SysFarmPalyerWarehouseRepository extends MongoRepository<SysFarmPlayerWarehouse, Long> {
    SysFarmPlayerWarehouse findByUserId(Long userId);
}
