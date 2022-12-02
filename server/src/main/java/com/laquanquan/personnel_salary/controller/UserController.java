package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.service.UserService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLDataException;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<Role> role(@RequestParam String token) {
        return userService.getRole(token);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<User> get(@RequestParam String token) {
        return userService.get(token);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponseBody<Object> update(@RequestBody User user) throws SQLDataException {
        return userService.updateUser(user);
    }
}
