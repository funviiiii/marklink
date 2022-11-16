package com.laquanquan.personnel_salary.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public String getAccount(Account account) {
        Account tmp = accountMapper.selectOne(account);
        if (tmp == null) {
            log.warn("找不到相应的账户");
            throw new DataNotFoundException("找不到相应的账户");
        }
        return tmp.getUid();
    }


    @Override
    public void signUp(Account account, User user) throws SQLDataException {
        // 对账户信息进行校验
        checkInfo(account);

        Account tmp = new Account();

        tmp.setUsername(account.getUsername());

        // 查询是否存在相同信息的账户
        if (accountMapper.selectOne(tmp) != null) {
            throw new AccountDuplicateException("账户已存在，请使用其他用户名");
        }
        if (account.getEmail() != null) {
            tmp.setUsername(null);
            tmp.setEmail(account.getEmail());
            if (accountMapper.selectOne(tmp) != null) {
                throw new AccountDuplicateException("邮箱已被占用，请使用其他邮箱");
            }
            tmp.setEmail(null);
        }
        tmp.setPhone(account.getPhone());
        if (accountMapper.selectOne(tmp) != null) {
            throw new AccountDuplicateException("手机号已被占用，请使用其他号码");
        }

        // 密码加密
        account.setPassword(Md5Utils.encode(account.getPassword()));

        // 分配随机编号
        String uid = "uid_" + RandomStringBuilder.build(10);
        account.setUid(uid);
        user.setUid(uid);

        // 数据转化为数据库格式
        formatData(user);

        // 持久化数据
        if (accountMapper.saveOne(account) != 1) {
            throw new SQLDataException("SQL执行过程出现异常");
        }
        if (userMapper.saveOne(user) != 1) {
            throw new SQLDataException("SQL执行过程出现异常");
        }
    }

    private void formatData(User user) {
        user.setGender(switch (user.getGender()) {
            case "男" -> "m";
            case "女" -> "f";
            case "保密" -> "s";
            default -> throw new IllegalArgumentException("Gender的值不合法");
        });
    }

    @Override
    public WebResponseBody<String> signIn(Account account) throws JsonProcessingException, AccessDeniedException {
        // 校验账户信息格式是否正确
        checkInfo(account);

        // 深拷贝并将密码设置为空，方便查询
        Account tmp = objectMapper.readValue(objectMapper.writeValueAsString(account), Account.class);
        tmp.setPassword(null);

        // 查询是否存在该账户
        if (accountMapper.selectOne(tmp) == null) {
            throw new DataNotFoundException("不存在该用户，请检查用户名是否正确!");
        }

        // 密码加密后进行校验
        account.setPassword(Md5Utils.encode(account.getPassword()));
        if (accountMapper.selectOne(account) == null) {
            // 返回空，密码不正确
            throw new AccessDeniedException("密码错误！请重试！");
        }

        // 密码正确，构造token并返回
        // token包含的信息内容: 用户id
        Map<String, Object> payload = new HashMap<>(1);
        payload.put("uid", account.getUid());

        String token = TokenBuilder.build(payload, 24 * 7);

        return new WebResponseBody<>("登录成功", token);
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
