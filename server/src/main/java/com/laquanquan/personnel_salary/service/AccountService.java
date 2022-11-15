package com.laquanquan.personnel_salary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.utils.WebResponseBody;

import java.nio.file.AccessDeniedException;
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


    /**
     * 登录业务
     *
     * @param account 账户对象
     * @return 响应体
     */
    WebResponseBody<String> signIn(Account account) throws JsonProcessingException, AccessDeniedException;
}
