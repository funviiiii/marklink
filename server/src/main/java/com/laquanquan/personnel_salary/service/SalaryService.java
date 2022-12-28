package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Salary;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.SalaryVO;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author lqq
 */
public interface SalaryService {
    /**
     * 获取工资列表
     *
     * @param token 用于获取对应工资的口令
     * @return 返回响应体
     * @exception AccessDeniedException 当提供的用户权限不足时，抛出该异常
     */
    WebResponseBody<List<SalaryVO>> getList(String token) throws AccessDeniedException;

    /**
     * 获取一条对象工资
     *
     * @param token 用于获取对应工资的口令
     * @return 返回响应体
     */
    WebResponseBody<SalaryVO> getOne(String token);

    /**
     * 更新一条工资信息
     *
     * @param salary 待更新的工资信息
     */
    void updateSalary(SalaryVO salary) throws AccessDeniedException;
}
