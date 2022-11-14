package com.laquanquan.personnel_salary.utils;

import java.util.Random;

/**
 * @author lqq
 */
public class RandomStringBuilder {
    private static final String BASE_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567809";

    private static final Random RANDOM = new Random();

    public static String build(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(BASE_STRING.charAt(RANDOM.nextInt(BASE_STRING.length())));
        }
        return stringBuilder.toString();
    }

    public static String buildInteger(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(BASE_STRING.charAt(RANDOM.nextInt(52, BASE_STRING.length())));
        }
        return stringBuilder.toString();
    }
}
