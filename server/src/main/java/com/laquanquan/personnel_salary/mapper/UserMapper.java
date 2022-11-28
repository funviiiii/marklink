package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lqq
 */
@Mapper
public interface UserMapper {

    /**
     * 将一条用户信息持久化到数据库
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    int saveOne(@Param("user") User user);

    /**
     * 根据用户编号查询一个用户对象
     *
     * @param uid 用于查询的用户编号
     * @return 返回用户对象
     */
    User selectByUid(String uid);
}




