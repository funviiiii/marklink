package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lqq
 */
@Mapper
public interface AccountMapper {

    /**
     * 根据uid查询一个对象
     *
     * @param uid 账户id
     * @return 返回账户对象
     */
    @Select("SELECT `uid`,`username`,`email`,`phone` FROM `t_account` WHERE `uid`=#{uid} AND `is_deleted`=0")
    Account selectOneByUid(@Param("uid") String uid);

    /**
     * 根据用户名查询一个账户对象
     *
     * @param username 账户用户名
     * @return 返回查询到的用户对象
     */
    @Select("SELECT `uid`,`username`,`email`,`phone` FROM `t_account` WHERE `username`=#{username} AND `is_deleted`=0")
    Account selectOneByUsername(String username);
}




