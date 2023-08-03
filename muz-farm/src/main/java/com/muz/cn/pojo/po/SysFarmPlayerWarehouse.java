package com.muz.cn.pojo.po;

import com.muz.cn.pojo.bo.GoodsInfo;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sys_farm_player_warehouse")
public class SysFarmPlayerWarehouse {
    @Id
    private Long id;
    private Long userId;
    private List<GoodsInfo> goodsList;
}
