package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.UserDataVO;

import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
import java.util.List;

/**
 * @author lqq
 */
public interface UserService {

    /**
     * 获取角色业务
     *
     * @param token 用于查询的token
     * @return 带有role数据的响应体
     */
    WebResponseBody<Role> getRole(String token);


    /**
     * 根据token获取用户数据
     *
     * @param token 前端存储的token
     * @return 数据响应体
     */
    WebResponseBody<User> get(String token);

    /**
     * 更新一条数据
     *
     * @param user 用于更改的用户信息
     * @return 返回响应体，载荷为空
     */
    WebResponseBody<Object> updateUser(User user) throws SQLDataException;

    /**
     * 根据当前管理员所属的部门获取部门支援
     *
     * @param token 存放了用户信息的token
     * @return 一个部门下的所有职员
     */
    WebResponseBody<List<UserDataVO>> getUserByDepartment(String token) throws AccessDeniedException;
}
