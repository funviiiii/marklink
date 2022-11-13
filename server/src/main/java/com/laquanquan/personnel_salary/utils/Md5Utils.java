package com.laquanquan.personnel_salary.utils;

import org.apache.tomcat.util.security.MD5Encoder;

/**
 * @author lqq
 */
public class Md5Utils {
    private static final String PRIVATE_SALT = "LQQ:";

    private static final String COMPLETE_STRING = "ZUOBIAOXIZUOBIAOXI";

    private static final int BASIC_LENGTH = 16;


    /**
     * 该方法是类方法，用于方便我们进行MD5单向加密，可以简单的对密码进行加密
     *
     * @param src 原字符串
     * @return 加密后的字符串
     */
    public static String encode(String src) {
        String targetSrc = "";
        if (src.length() < BASIC_LENGTH && src.length() >= BASIC_LENGTH - PRIVATE_SALT.length()) {
            targetSrc = src + COMPLETE_STRING.substring(0, BASIC_LENGTH - src.length());
        } else if (src.length() < BASIC_LENGTH - PRIVATE_SALT.length()) {
            targetSrc = PRIVATE_SALT + src + COMPLETE_STRING.substring(0, BASIC_LENGTH - src.length() - PRIVATE_SALT.length());
        } else if (src.length() == BASIC_LENGTH) {
            return MD5Encoder.encode(src.getBytes());
        }
        return MD5Encoder.encode(targetSrc.getBytes());
    }
}
