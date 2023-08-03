package com.muz.cn.repository;

import com.muz.cn.pojo.po.SysFarmPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysFarmPalyerRepository extends JpaRepository<SysFarmPlayer, Long> {
    SysFarmPlayer findByUserId(Long userId);
}
