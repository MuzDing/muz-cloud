package com.muz.cn.pojo.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_farm_player_level ")
public class SysFarmPlayerLevel  {
    @Id
    private Long id;
    private Integer level;
    private Integer exp;
    private Integer LandNumber;
}
