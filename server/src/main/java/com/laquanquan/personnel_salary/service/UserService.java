package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Role;
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
}
