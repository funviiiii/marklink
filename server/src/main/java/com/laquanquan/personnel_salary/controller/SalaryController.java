package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.service.SalaryService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.SalaryVO;
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
    public WebResponseBody<SalaryVO> getOwn(@RequestParam String token) {
        return salaryService.getOne(token);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WebResponseBody<List<SalaryVO>> getList(@RequestParam String token) throws AccessDeniedException {
        return salaryService.getList(token);
    }
}
