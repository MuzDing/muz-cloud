package com.muz.cn.pojo.po;

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
    private Integer type;
    private Integer number;
    // 1 锁定 0未锁定
    private Integer lock;
}
