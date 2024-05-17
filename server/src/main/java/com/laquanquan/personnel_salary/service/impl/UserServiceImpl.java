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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 将数据内容更新为对应的编号而不是中文
        Department department = departmentMapper.selectByName(user.getDepartment());
        Role role = roleMapper.selectByName(user.getRole());

        user.setDepartment(department.getDid());
        user.setRole(role.getRid());

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
        List<UserDataVO> userData;

        // 获取部门列表
        Map<String, String> departments = convertDepartmentListToMap(departmentMapper.selectAll());

        // 获取职位列表
        Map<String, String> roles = convertRoleListToMap(roleMapper.selectAll());

        if (user.getRole().equals("rid_super")) {
            // 作为超级管理员，可以获取所有信息
            userData = userMapper.selectAll();
        } else {
            // 不是超级管理员时，根据部门获取本部门的用户列表
            // 获取某部门下的用户列表
            userData = userMapper.selectByDepartment(user.getDepartment());
        }
        return new WebResponseBody<>("获取用户列表成功",
                dataProcessing(userData, departments, roles));
    }

    /**
     * 将部门列表转化成键值对的形式，方便数据处理（部门ID-部门名）
     */
    private Map<String, String> convertDepartmentListToMap(List<Department> departmentList) {
        Map<String, String> departmentMap = new HashMap<>();
        for (Department department : departmentList) {
            departmentMap.put(department.getDid(), department.getDepartmentName());
        }
        return departmentMap;
    }

    /**
     * 将角色列表转化成键值对的形式，方便数据处理（角色ID-角色名）
     */
    private Map<String, String> convertRoleListToMap(List<Role> roleList) {
        Map<String, String> roleMap = new HashMap<>();
        for (Role role : roleList) {
            roleMap.put(role.getRid(), role.getRoleName());
        }
        return roleMap;
    }

    /**
     * 数据处理：
     * 将用户列表中的部门ID，角色ID转化为部门名和角色名，并将性别转换成可读的值
     */
    private List<UserDataVO> dataProcessing(List<UserDataVO> userList, Map<String, String> departmentMap, Map<String, String> roleMap) {
        for (UserDataVO userDataVO : userList) {
            userDataVO.setDepartment(departmentMap.get(userDataVO.getDepartment()));
            userDataVO.setRole(roleMap.get(userDataVO.getRole()));
            // 将用户对象中的性别信息替换成可读的值
            userDataVO.setGender(switch (userDataVO.getGender()) {
                case "m" -> "男";
                case "f" -> "女";
                case "s" -> "保密";
                default -> null;
            });
        }
        return userList;
    }
}
