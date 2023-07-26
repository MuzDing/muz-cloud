package com.muz.cn.pojo.baseEnum;

public enum FarmBehaviorEnum {
    /**
     * 农场开头为 1 数字长度为8位.
     * 第二位为 增删改查 增为1,删为2,改为3,查为4
     * 第三位第四位为 具体内容 比如查人,查地,查商店
     * 最后四位为累加shu
     */
    // 查询当前土地信息
    FIND_ALL_LANDS(14000001, "查询所有土地"),
    BUY_GOODS(11000001, "购买商品"),
    PLANT_GOODS(13000001, "种植商品"),
    HARVEST_GOODS(13000002, "出售商品"),
    ;
    private Integer code;
    private String desc;

    FarmBehaviorEnum(int code, String desc) {
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
        for (FarmBehaviorEnum ele : FarmBehaviorEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele.getDesc();
            }
        }
        return null;
    }

}
