package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.utils.WebResponseBody;

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
}
