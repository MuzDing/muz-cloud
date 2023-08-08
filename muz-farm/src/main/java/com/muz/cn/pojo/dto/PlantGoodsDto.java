package com.muz.cn.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantGoodsDto extends BaseDataDto{
    @NotNull
    private Integer goodsId;
    @NotNull
    private Integer goodsType;
    @NotNull
    private Integer goodsNum;

}
