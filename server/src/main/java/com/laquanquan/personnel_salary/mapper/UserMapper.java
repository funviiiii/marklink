package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.User;
import org.apache.ibatis.annotations.Param;

/**
* @author lqq
*/
public interface UserMapper {

    /**
     * 将一条用户信息持久化到数据库
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    int saveOne(@Param("user") User user);
}




