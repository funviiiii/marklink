package com.laquanquan.personnel_salary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {
    @Test
    void encode() {
        System.out.println(Md5Utils.encode("password1"));
        System.out.println(Md5Utils.encode("password2"));
        System.out.println(Md5Utils.encode("password3"));
    }
}