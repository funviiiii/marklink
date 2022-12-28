package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.vo.SalaryVO;
import com.laquanquan.personnel_salary.vo.UserDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lqq
 */
@Mapper
public interface SalaryMapper {

    /**
     * 根据用户编号获取用户的工资信息
     *
     * @param uid 用户编号
     * @return 查询到的工资对象
     */
    @Select("SELECT `uid`, `basic_salary`, `allowance`, `reward`, `should_pay`, `cost`, `insurance`, `real_salary` FROM `t_salary` WHERE `uid`=#{uid} AND `is_deleted`=0 LIMIT 1")
    SalaryVO selectOneByUid(String uid);

    /**
     * 根据用户列表查询工资列表
     *
     * @param userList 用户列表
     * @return 查询到的工资列表
     */
    List<SalaryVO> selectListByDepartment(List<UserDataVO> userList);
}




