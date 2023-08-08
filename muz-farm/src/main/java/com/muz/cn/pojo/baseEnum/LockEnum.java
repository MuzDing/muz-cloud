package com.muz.cn.pojo.baseEnum;

public enum LockEnum {
    LOCK(1, "锁定"),
    NOT_LOCK(0, "未锁定")

    ;
    private Integer code;
    private String desc;

    LockEnum(int code, String desc) {
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

    public static LockEnum getLockEnum(Integer code) {
        for (LockEnum ele : LockEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
