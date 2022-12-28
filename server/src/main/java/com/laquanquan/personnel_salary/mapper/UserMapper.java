package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.vo.UserDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 根据部门获取所有该部门下的职员
     *
     * @param department 用于查询的部门编号
     * @return 返回对应部门下的所有职员
     */
    @Select("SELECT `uid`, `name`, `gender`, `birthday`, `induction`, `department`, `role`, `is_married` AS 'isMarried' , `resume` FROM `t_user` WHERE `department`=#{department} AND `is_deleted`=0")
    List<UserDataVO> selectByDepartment(String department);

    /**
     * 更新一条用户数据
     *
     * @param user 用于更新数据的对象
     * @return 影响表的行数
     */
    int updateOne(@Param("user") User user);
}




