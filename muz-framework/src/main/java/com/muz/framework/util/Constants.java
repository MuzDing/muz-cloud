package com.muz.framework.util;


public class Constants {

    /**
     * 应用前后端字段：0表示未获取响应结果（异常），1表示有响应结果（正常），-1表示未登录或无权限
     */
    public static final Integer SUCCESS = 1;
    public static final Integer FAILED = 0;
    public static final Integer NOT_LOGIN = -1;

    public static final String ENCODING = "UTF-8";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    public static final String SEPARATOR_SIGN = ",";
    public static final String NULL = "null";
    public static final String HTTP = "http";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 分页记录行大小
     */
    public static final int PAGE_SIZE=10;

    /**
     * 时间常量
     */
    public final static String DAY = "day";
    public final static String HOUR = "hour";
    public final static String MIN = "min";
    /**
     * 日期格式化常量
     */
    public final static String DATE_FORMAT_DAY = "yyyyMMdd";
    public final static String DATE_FORMAT_HOUR = "HH";
    public final static String DATE_FORMAT_MIN = "mm";

}
