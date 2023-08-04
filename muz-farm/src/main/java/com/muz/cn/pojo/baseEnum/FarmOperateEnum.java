package com.muz.cn.pojo.baseEnum;

public enum FarmOperateEnum {
    /**
     * 农场开头为 1 数字长度为8位.
     * 第二位为 增删改查 增为1,删为2,改为3,查为4
     * 第三位第四位为 具体内容 比如查人,查地,查商店
     * 最后四位为累加shu
     */
    // 查询当前土地信息
    FIND_ALL_LANDS(14000001, "查询所有土地"),
    FIND_WAREHOUSE(14000002, "获取仓库信息"),
    FIND_FARM_PALYER_INFO(14000003, "获取农场玩家信息"),
    BUY_GOODS(11000001, "购买商品"),
    PLANT_GOODS(13000001, "种植作物"),
    SELL_GOODS(13000002, "出售商品"),
    HARVEST_GOODS(13000003, "收获土地上中的作物"),

    ;
    private Integer code;
    private String desc;

    FarmOperateEnum(int code, String desc) {
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

    public static FarmOperateEnum getFarmOperateEnum(Integer code) {
        for (FarmOperateEnum ele : FarmOperateEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }

}
