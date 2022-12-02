package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    Role selectByRid(String rid);

    List<Role> selectAll();
}




