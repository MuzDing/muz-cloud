package com.muz.cn.util;

import java.lang.reflect.Field;
import java.util.Map;

public class BaseUtils {
    public static <T> T convertToClass(Object obj, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T object = clazz.newInstance();
        Map<String, Object> map = null;
        if( obj instanceof Map){
            map = ((Map<String, Object>)obj);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
            } catch (NoSuchFieldException e) {
                // 如果HashMap中的键在目标类中不存在对应的属性，可以选择忽略或进行其他处理
                // 这里直接忽略，继续处理下一个键值对
                continue;
            }
        }

        return object;
    }
    public static Long Double2Seconds(Double hours) {
        return (long)(hours * 60) * 60; // 将小时数乘以 60 转换为分钟数
    }

//    public static <T> T convertToClass(Map<String, String> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
//        T object = clazz.newInstance();
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String fieldName = entry.getKey();
//            String fieldValue = entry.getValue();
//
//            try {
//                Field field = clazz.getDeclaredField(fieldName);
//                field.setAccessible(true);
//                field.set(object, fieldValue);
//            } catch (NoSuchFieldException e) {
//                // 如果HashMap中的键在目标类中不存在对应的属性，可以选择忽略或进行其他处理
//                // 这里直接忽略，继续处理下一个键值对
//                continue;
//            }
//        }
//
//        return object;
//    }
}
