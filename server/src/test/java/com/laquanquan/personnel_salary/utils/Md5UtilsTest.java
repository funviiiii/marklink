package com.laquanquan.personnel_salary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {
    @Test
    void encode() {
        System.out.println(Md5Utils.encode("password1"));
        System.out.println(Md5Utils.encode("password2"));
        System.out.println(Md5Utils.encode("password3"));
        System.out.println(Md5Utils.encode("testroot"));
        System.out.println(Md5Utils.encode("password4"));
        System.out.println(Md5Utils.encode("password5"));
        System.out.println(Md5Utils.encode("password6"));
        System.out.println(Md5Utils.encode("password7"));
        System.out.println(Md5Utils.encode("password8"));
        System.out.println(Md5Utils.encode("password9"));
    }
}