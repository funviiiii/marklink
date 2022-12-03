package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Account;
import org.apache.ibatis.annotations.Delete;
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
     * @param account 账户对象
     * @return 返回账户对象
     */
    Account selectOne(@Param("account") Account account);

    /**
     * 将一个用户持久化
     *
     * @param account 账户信息
     * @return 影响的行数
     */
    int saveOne(@Param("account") Account account);

    /**
     * 根据用户编号真实的删除一条数据
     *
     * @param uid 用户编号
     * @return 影响行数
     */
    @Delete("DELETE FROM `t_account` WHERE `uid`=#{uid} LIMIT 1")
    int hardDeleteByUid(String uid);

    int updateOne(@Param("account") Account account);
}




