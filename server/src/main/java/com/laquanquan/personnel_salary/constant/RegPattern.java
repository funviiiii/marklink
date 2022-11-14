package com.laquanquan.personnel_salary.constant;

import java.util.regex.Pattern;

/**
 * @author lqq
 */
public class RegPattern {
    public static final Pattern EMAIL_REG = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$");

    public static final Pattern ACCOUNT_REG = Pattern.compile("^[\\w.]{6,16}$");
}
