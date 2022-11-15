package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.User;

import java.sql.SQLDataException;

/**
 * @author lqq
 */
public interface AccountService {

    /**
     * 根据uid查询一个账户
     *
     * @param account 封装好的账户对象
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccount(Account account);


    /**
     * 注册业务
     *
     * @param account 账户信息
     * @param user    用户信息
     */
    void signUp(Account account, User user) throws SQLDataException;
}
