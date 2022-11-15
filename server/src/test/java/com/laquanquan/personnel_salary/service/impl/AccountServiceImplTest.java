package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.exception.AccountDuplicateException;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.exception.UserInfoInvalidException;
import com.laquanquan.personnel_salary.mapper.AccountMapper;
import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.Md5Utils;
import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLDataException;
import java.util.Date;

@SpringBootTest
@Transactional
class AccountServiceImplTest {

    @Resource
    private AccountService accountService;

    private static Account account;

    @BeforeAll
    static void init(@Autowired AccountMapper accountMapper) {
        Account account = new Account();
        account.setUid(RandomStringBuilder.build(10));
        account.setUsername(RandomStringBuilder.build(10));
        account.setPassword(Md5Utils.encode(RandomStringBuilder.build(10)));
        account.setEmail(RandomStringBuilder.build(10) + "@qq.com");
        account.setPhone(RandomStringBuilder.buildInteger(11));
        AccountServiceImplTest.account = account;
        accountMapper.saveOne(account);
    }

    @Test
    void getAccount() {
        // 正常通过
        Assertions.assertEquals(account.getUid(), accountService.getAccount(account));

        // 用户不存在，抛出异常
        Account tmp = new Account();
        tmp.setUsername(RandomStringBuilder.build(10));
        Assertions.assertThrows(DataNotFoundException.class,
                () -> accountService.getAccount(tmp));
    }

    @Test
    void signUp() {
        // 初始化数据对象
        Account tmpAccount = new Account();
        User tmpUser = new User();

        // 初始化数据
        tmpAccount.setUsername(RandomStringBuilder.build(20));
        tmpAccount.setPassword(RandomStringBuilder.build(20));
        tmpAccount.setEmail(account.getEmail());
        tmpAccount.setPhone(account.getPhone());
        tmpUser.setName(RandomStringBuilder.build(5));
        tmpUser.setGender("男");
        tmpUser.setBirthday(new Date());
        tmpUser.setIsMarried(0);

        // 用户名过长
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setUsername(RandomStringBuilder.build(3));

        // 用户名过短
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setUsername(RandomStringBuilder.build(10) + "\\[]");

        // 用户名含有特殊字符
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setUsername(RandomStringBuilder.build(10));

        //密码过长
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setPassword(RandomStringBuilder.build(3));

        //密码过短
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setPassword(RandomStringBuilder.build(10) + "\\[]");

        //密码含有特殊字符
        Assertions.assertThrows(UserInfoInvalidException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户信息格式有误，请重试");
        tmpAccount.setPassword(RandomStringBuilder.build(10));

        // 重复用户名
        Assertions.assertThrows(AccountDuplicateException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "账户已存在，请使用其他用户名");
        tmpAccount.setUsername(RandomStringBuilder.build(10));

        // 重复邮箱
        Assertions.assertThrows(AccountDuplicateException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "邮箱已被占用，请使用其他邮箱");
        tmpAccount.setEmail(RandomStringBuilder.build(10) + "@qq.com");

        // 重复手机号
        Assertions.assertThrows(AccountDuplicateException.class,
                () -> accountService.signUp(tmpAccount, tmpUser), "手机号已被占用，请使用其他号码");
        tmpAccount.setPhone(RandomStringBuilder.buildInteger(11));

        // 注册成功
        Assertions.assertDoesNotThrow(() -> accountService.signUp(tmpAccount, tmpUser));
    }

    @AfterAll
    static void clear(@Autowired AccountMapper accountMapper) throws SQLDataException {
        if (accountMapper.hardDeleteByUid(account.getUid()) != 1) {
            throw new SQLDataException("清理测试数据失败");
        }
    }
}