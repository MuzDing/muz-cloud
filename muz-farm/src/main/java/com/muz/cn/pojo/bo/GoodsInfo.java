package com.muz.cn.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
    private Integer goodsId;
    private Integer goodsType;
    private Integer goodsNumber;
    // 1 锁定 0未锁定
    private Integer lock;
}
