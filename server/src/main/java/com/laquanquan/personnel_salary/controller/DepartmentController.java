package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Department;
import com.laquanquan.personnel_salary.mapper.DepartmentMapper;
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
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentMapper departmentMapper;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<List<Department>> getAllDepartment() {
        return new WebResponseBody<>("查询成功", departmentMapper.selectAll());
    }
}
