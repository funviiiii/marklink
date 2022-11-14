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

    /**
     * 根据邮箱查询一个账户对象
     *
     * @param email 邮箱
     * @return 返回查询到的用户对象
     */
    @Select("SELECT `uid`,`username`,`email`,`phone` FROM `t_account` WHERE `email`=#{email} AND `is_deleted`=0")
    Account selectOneByEmail(String email);

    /**
     * 根据手机查询一个账户对象
     *
     * @param phone 邮箱
     * @return 返回查询到的用户对象
     */
    @Select("SELECT `uid`,`username`,`email`,`phone` FROM `t_account` WHERE `phone`=#{phone} AND `is_deleted`=0")
    Account selectOneByPhone(String phone);

    /**
     * 将一个用户持久化
     *
     * @param account 账户信息
     * @return 影响的行数
     */
    int saveOne(@Param("account") Account account);
}




