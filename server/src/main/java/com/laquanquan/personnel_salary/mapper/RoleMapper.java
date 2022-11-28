package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author lqq
*/
@Mapper
public interface RoleMapper {
    /**
     * 根据编号查询一个职位
     *
     * @param rid 用于查询的职位编号
     * @return 返回的职位对象
     */
    @Select("SELECT `rid`, `role_name`, `personnel_right`, `salary_right`, `info_right`, `advance_right` FROM `t_role` WHERE `rid`=#{rid}")
    Role selectByRid(String rid);
}




