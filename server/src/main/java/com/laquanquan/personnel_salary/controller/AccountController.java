package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.SignUpVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLDataException;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 查询一个用户
     *
     * @param account 用户对象
     * @return 返回一个uid，若没有查询到则返回空
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAccount(@RequestBody Account account) {
        return accountService.getAccount(account);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponseBody<String> signUp(@RequestBody SignUpVO signUpVO) throws SQLDataException {
        accountService.signUp(signUpVO.getAccount(), signUpVO.getUser());
        return new WebResponseBody<>("注册成功");
    }
}
