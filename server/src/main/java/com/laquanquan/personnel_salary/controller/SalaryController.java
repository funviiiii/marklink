package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.service.SalaryService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.SalaryVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Resource
    private SalaryService salaryService;

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<SalaryVO> getOwn(@RequestParam String token) {
        return salaryService.getOne(token);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<List<SalaryVO>> getList(@RequestParam String token) throws AccessDeniedException {
        return salaryService.getList(token);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody SalaryVO salary) throws AccessDeniedException {
        salaryService.updateSalary(salary);
    }
}
