package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Department;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lqq
*/
@Mapper
public interface DepartmentMapper {
    /**
     * 根据部门编号查询一个部门对象
     *
     * @param did 部门编号
     * @return 查询到的部门对象
     */
    Department selectByDid(String did);
}




