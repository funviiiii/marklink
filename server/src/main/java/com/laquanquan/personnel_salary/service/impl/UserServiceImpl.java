package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.mapper.RoleMapper;
import com.laquanquan.personnel_salary.mapper.UserMapper;
import com.laquanquan.personnel_salary.service.UserService;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lqq
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public WebResponseBody<Role> getRole(String token) {
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);
        Role role = roleMapper.selectByRid(user.getRole());
        return new WebResponseBody<>("获取用户对象成功", role);
    }
}
