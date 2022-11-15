package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.constant.RegPattern;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.exception.AccountDuplicateException;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.exception.UserInfoInvalidException;
import com.laquanquan.personnel_salary.mapper.AccountMapper;
import com.laquanquan.personnel_salary.mapper.UserMapper;
import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.Md5Utils;
import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLDataException;
/**
 * @author lqq
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public String getAccountById(String uid) {
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
            log.warn("找不到用户名为 {} 的对象", username);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }

    @Override
    public String getAccountByEmail(String email) {
        Account account = accountMapper.selectOneByEmail(email);
        if (account == null) {
            log.warn("找不到邮箱为 {} 的对象", email);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }

    @Override
    public String getAccountByPhone(String phone) {
        Account account = accountMapper.selectOneByPhone(phone);
        if (account == null) {
            log.warn("找不到电话号码为 {} 的对象", phone);
            throw new DataNotFoundException("找不到相应的账户");
        }
        return account.getUid();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void signUp(Account account, User user) throws SQLDataException {
        // 对账户信息进行校验
        checkInfo(account);

        // 查询是否存在相同信息的账户
        if (accountMapper.selectOneByUsername(account.getUsername()) != null) {
            throw new AccountDuplicateException("账户已存在，请使用其他用户名");
        }
        if (accountMapper.selectOneByEmail(account.getEmail()) != null) {
            throw new AccountDuplicateException("邮箱已被占用，请使用其他邮箱");
        }
        if (accountMapper.selectOneByPhone(account.getPhone()) != null) {
            throw new AccountDuplicateException("手机号已被占用，请使用其他号码");
        }

        // 密码加密
        account.setPassword(Md5Utils.encode(account.getPassword()));

        // 分配随机编号
        String uid = "uid_" + RandomStringBuilder.build(10);
        account.setUid(uid);
        user.setUid(uid);

        // 持久化数据
        if (accountMapper.saveOne(account) != 1) {
            throw new SQLDataException("SQL执行过程出现异常");
        }
        if (userMapper.saveOne(user) != 1) {
            throw new SQLDataException("SQL执行过程出现异常");
        }
    }

    /**
     * 校验账户信息的有效性
     *
     * @param account 账户信息
     */
    private void checkInfo(Account account) {
        if ((!RegPattern.ACCOUNT_REG.matcher(account.getUsername()).find()) ||
                (!RegPattern.ACCOUNT_REG.matcher(account.getPassword()).find())) {
            throw new UserInfoInvalidException("账户信息格式有误，请重试");
        }
    }
}
