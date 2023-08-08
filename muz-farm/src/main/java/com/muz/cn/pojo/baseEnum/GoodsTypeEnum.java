package com.muz.cn.pojo.baseEnum;

public enum GoodsTypeEnum {
    PLANT_SEEDS(100, "植物种子"),
    PLANT_FRUIT(101, "植物果实"),
    PLANT_TRUNK(102, "植物躯干"),
    ANIMAL_BABY(200, "动物幼仔"),
    ANIMAL_ADULT(201, "成年动物"),
    ;
    private Integer code;
    private String desc;

    GoodsTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public static String getDescByCode(Integer code) {
        for (FarmOperateEnum ele : FarmOperateEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele.getDesc();
            }
        }
        return null;
    }

    public static GoodsTypeEnum getCodeEnum(Integer code) {
        for (GoodsTypeEnum ele : GoodsTypeEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
