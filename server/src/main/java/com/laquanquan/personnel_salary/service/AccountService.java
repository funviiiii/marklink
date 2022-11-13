package com.laquanquan.personnel_salary.service;

/**
 * @author lqq
 */
public interface AccountService {

    /**
     * 根据uid查询一个账户对象
     *
     * @param uid 用户编号
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccountById(String uid);
}
