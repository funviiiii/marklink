package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Department;
import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.mapper.DepartmentMapper;
import com.laquanquan.personnel_salary.mapper.RoleMapper;
import com.laquanquan.personnel_salary.mapper.UserMapper;
import com.laquanquan.personnel_salary.service.UserService;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.UserDataVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
import java.util.List;

/**
 * @author lqq
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public WebResponseBody<Role> getRole(String token) {
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);
        Role role = roleMapper.selectByRid(user.getRole());
        return new WebResponseBody<>("获取职位对象成功", role);
    }

    @Override
    public WebResponseBody<User> get(String token) {
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);

        // 将用户对象中的职位编号替换为具体的职位名字
        Role role = roleMapper.selectByRid(user.getRole());
        user.setRole(role.getRoleName());

        // 将用户对象中的部门编号替换为具体的部门名字
        Department department = departmentMapper.selectByDid(user.getDepartment());
        user.setDepartment(department.getDepartmentName());

        // 将用户对象中的性别信息替换成可读的值
        user.setGender(switch (user.getGender()) {
            case "m" -> "男";
            case "f" -> "女";
            case "s" -> "保密";
            default -> null;
        });
        return new WebResponseBody<>("获取用户对象成功", user);
    }

    @Override
    public WebResponseBody<Object> updateUser(User user) throws SQLDataException {
        user.setGender(switch (user.getGender()) {
            case "男" -> "m";
            case "女" -> "f";
            case "保密" -> "s";
            default -> null;
        });
        if (userMapper.updateOne(user) != 1) {
            throw new SQLDataException("更新数据失败");
        }
        return new WebResponseBody<>("更新数据成功");
    }

    @Override
    public WebResponseBody<List<UserDataVO>> getUserByDepartment(String token) throws AccessDeniedException {
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);

        // 校验权限
        Role role = roleMapper.selectByRid(user.getRole());
        if (!role.getPersonnelRight()) {
            throw new AccessDeniedException("权限不足，不允许访问！");
        }

        // 获取某部门下的用户列表
        List<UserDataVO> userData = userMapper.selectByDepartment(user.getDepartment());

        // 获取部门名
        String department = departmentMapper.selectByDid(user.getDepartment()).getDepartmentName();

        // 查询所有职位信息
        List<Role> roles = roleMapper.selectAll();

        // 对数据内容进行处理
        for (UserDataVO item : userData) {
            item.setDepartment(department);
            for (Role roleItem : roles) {
                if (roleItem.getRid().equals(item.getRole())) {
                    item.setRole(roleItem.getRoleName());
                    break;
                }
            }
            // 将用户对象中的性别信息替换成可读的值
            item.setGender(switch (user.getGender()) {
                case "m" -> "男";
                case "f" -> "女";
                case "s" -> "保密";
                default -> null;
            });
        }
        return new WebResponseBody<>("获取用户列表成功", userData);
    }
}
