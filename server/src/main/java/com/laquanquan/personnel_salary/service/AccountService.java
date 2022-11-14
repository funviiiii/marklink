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
     * @param uid 用户编号
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccountById(String uid);

    /**
     * 根据用户名查询一个账户
     *
     * @param username 用户名
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccountByUsername(String username);

    /**
     * 根据邮箱查询一个账户
     *
     * @param email 用户邮箱
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccountByEmail(String email);

    /**
     * 根据手机号查询一个对象
     *
     * @param phone 手机号
     * @return 获取到的用户编号，若没查询到则返回空
     */
    String getAccountByPhone(String phone);


    /**
     * 注册业务
     *
     * @param account 账户信息
     * @param user    用户信息
     */
    void signUp(Account account, User user) throws SQLDataException;
}
