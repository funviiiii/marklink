package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.mapper.AccountMapper;
import com.laquanquan.personnel_salary.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lqq
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public String getAccountById(String uid) {
        // 查询账户对象
        Account account = accountMapper.selectOneByUid(uid);
        if (account == null) {
            log.warn("找不到uid为 {} 的对象", uid);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return uid;
    }

    @Override
    public String getAccountByUsername(String username) {
        Account account = accountMapper.selectOneByUsername(username);
        if (account == null) {
            log.warn("找不到用户名 为 {} 的对象", username);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }

    @Override
    public String getAccountByEmail(String email) {
        Account account = accountMapper.selectOneByEmail(email);
        if (account == null) {
            log.warn("找不到用户名 为 {} 的对象", email);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }

    @Override
    public String getAccountByPhone(String phone) {
        Account account = accountMapper.selectOneByPhone(phone);
        if (account == null) {
            log.warn("找不到用户名 为 {} 的对象", phone);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }
}
