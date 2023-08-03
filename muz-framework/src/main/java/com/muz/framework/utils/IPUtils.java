package com.muz.framework.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * ip 工具类
 */
public class IPUtils {
    private static final String IPV4 = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final String IPV6 = "^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$";



    /**
     * 判断一个IP地址是否为IPv4地址
     */
    public static boolean isIpv4(String ip) {
        return ip.matches(IPV4);
    }

    /**
     * 判断一个IP地址是否为IPv6地址
     */
    public static boolean isIpv6(String ip) {
        return ip.matches(IPV6);
    }


    /**
     * 获取 ip 地址
     * @param request
     * @return
     */
    public static String getIpByHttpServletRequest(HttpServletRequest request){
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }


        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}