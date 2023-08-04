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
@Table(name = "sys_farm_land")
public class SysFarmLand {
    @Id
    private Long id;
    private Long userId;
    private Integer goodsId;
    private Integer landId;
    private LocalDateTime planting_time;
    private Double maturity_time;
    private Integer maturity_times;

}


