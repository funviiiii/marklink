package com.laquanquan.personnel_salary;

import com.laquanquan.personnel_salary.service.AccountService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 根据id查询一个用户
     *
     * @param uid uid
     * @return 返回一个uid，若没有查询到则返回空
     */
    @ResponseBody
    @ResponseStatus
    @RequestMapping(method = RequestMethod.GET, value = "/uid/{uid}")
    public String getAccount(@PathVariable String uid) {
        return accountService.getAccountById(uid);
    }
}
