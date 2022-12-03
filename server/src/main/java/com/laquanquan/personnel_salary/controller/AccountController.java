package com.laquanquan.personnel_salary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laquanquan.personnel_salary.constant.RedisKey;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.PasswordUpdateVO;
import com.laquanquan.personnel_salary.vo.SignUpVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 注册控制器
     *
     * @param signUpVO 注册数据对象，承载前端数据
     * @return 响应体
     * @throws SQLDataException      若填入表时出错则抛出该异常
     * @throws AccessDeniedException 若邮箱验证码不通过，则抛出该异常
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponseBody<String> signUp(@RequestBody SignUpVO signUpVO) throws SQLDataException, AccessDeniedException {
        if (signUpVO.getEmail() != null) {
            checkVerifyCode(signUpVO.getEmail(), signUpVO.getVerificationCode());
        }
        accountService.signUp(signUpVO.getAccount(), signUpVO.getUser());
        return new WebResponseBody<>("注册成功");
    }

    /**
     * 查询一个用户
     *
     * @param account 用户对象
     * @return 返回一个uid，若没有查询到则返回空
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public WebResponseBody<Account> getAccount(@RequestBody Account account) {
        return accountService.getAccount(account);
    }

    /**
     * 自动登录
     *
     * @param token 前端传来的token
     * @return 新创建的token
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/autologin")
    public WebResponseBody<String> autoLogin(@RequestParam String token) {
        Account account = new Account();
        account.setUid((String) TokenBuilder.parse(token).get("uid"));
        account = getAccount(account).getContent();
        if (account == null) {
            // 没查询到相关用户
            throw new DataNotFoundException("用户自动登录失败");
        }
        Map<String, Object> payload = new HashMap<>(1);
        payload.put("uid", account.getUid());
        return new WebResponseBody<>("登录成功", TokenBuilder.build(payload, 7 * 24));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public WebResponseBody<Account> getAccount(@RequestParam String token) {
        Account account = new Account();
        account.setUid((String) TokenBuilder.parse(token).get("uid"));
        account = getAccount(account).getContent();
        if (account == null) {
            // 没查询到相关用户
            throw new DataNotFoundException("获取用户信息失败");
        }
        return new WebResponseBody<>("获取账户信息成功", account);
    }


    /**
     * 校验邮箱和验证码是否正确
     *
     * @param email            邮箱
     * @param verificationCode 验证码
     * @throws AccessDeniedException 若信息不正确，则抛出访问限制异常
     */
    private void checkVerifyCode(String email, String verificationCode) throws AccessDeniedException {
        if (verificationCode != null) {
            if (!Objects.equals(stringRedisTemplate.opsForValue().get(RedisKey.VERIFICATION_CODE_KEY + email), verificationCode)) {
                throw new AccessDeniedException("请输入邮箱验证码");
            }
        } else {
            throw new AccessDeniedException("请输入邮箱验证码");
        }
    }

    /**
     * 登录控制器
     *
     * @param account 账户信息
     * @return 响应体
     * @throws AccessDeniedException   若信息有误，抛出访问限制异常
     * @throws JsonProcessingException 若对象映射不正确，则抛出Json转换异常
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<String> login(@RequestBody Account account) throws AccessDeniedException, JsonProcessingException {
        return accountService.login(account);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public WebResponseBody<Object> updatePassword(@RequestBody PasswordUpdateVO passwordUpdateVO) throws AccessDeniedException {
        return accountService.updatePassword(passwordUpdateVO);
    }
}
