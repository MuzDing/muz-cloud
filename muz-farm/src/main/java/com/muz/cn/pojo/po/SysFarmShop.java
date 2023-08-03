package com.muz.cn.pojo.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_farm_shop")
public class SysFarmShop {
    @Id
    private Long id;
    private Integer goodsId;
    private String name;
    private Integer type;
    private String image;
    private Integer goodsPrice;
    private Integer maturityPrice;
    private Integer maturityPumber;
    private Integer maturityPimes;
    private Integer maturityPime;
    private Integer maturityExp;
    private Integer useState;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
