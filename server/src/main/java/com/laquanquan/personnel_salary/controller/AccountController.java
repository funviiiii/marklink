package com.laquanquan.personnel_salary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laquanquan.personnel_salary.constant.RedisKey;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.SignUpVO;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
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
     * 查询一个用户
     *
     * @param account 用户对象
     * @return 返回一个uid，若没有查询到则返回空
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/get")
    public String getAccount(@RequestBody Account account) {
        return accountService.getAccount(account);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponseBody<String> signUp(@RequestBody SignUpVO signUpVO) throws SQLDataException, AccessDeniedException {
        if (signUpVO.getEmail() != null) {
            checkVerifyCode(signUpVO.getEmail(), signUpVO.getVerificationCode());
        }
        accountService.signUp(signUpVO.getAccount(), signUpVO.getUser());
        return new WebResponseBody<>("注册成功");
    }

    private void checkVerifyCode(String email, String verificationCode) throws AccessDeniedException {
        if (verificationCode != null) {
            if (!Objects.equals(stringRedisTemplate.opsForValue().get(RedisKey.VERIFICATION_CODE_KEY + email), verificationCode)) {
                throw new AccessDeniedException("请输入邮箱验证码");
            }
        } else {
            throw new AccessDeniedException("请输入邮箱验证码");
        }
    }

    public WebResponseBody<String> signIn(@RequestBody Account account) throws AccessDeniedException, JsonProcessingException {
        return accountService.signIn(account);
    }
}
