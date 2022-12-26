package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.mapper.RoleMapper;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleMapper roleMapper;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<List<Role>> getAllRole() {
        return new WebResponseBody<>("查询成功", roleMapper.selectAll());
    }
}
