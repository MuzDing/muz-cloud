package com.muz.cn.pojo.dto;

import com.muz.cn.pojo.bo.GoodsInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellGoodsDto extends BaseDataDto {

    @NotNull
    private List<GoodsInfo> goodsList;
}
