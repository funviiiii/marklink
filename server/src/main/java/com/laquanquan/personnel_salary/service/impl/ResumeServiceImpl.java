package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.Resume;
import com.laquanquan.personnel_salary.domain.Role;
import com.laquanquan.personnel_salary.domain.User;
import com.laquanquan.personnel_salary.exception.DataDuplicationException;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.mapper.ResumeMapper;
import com.laquanquan.personnel_salary.mapper.RoleMapper;
import com.laquanquan.personnel_salary.mapper.UserMapper;
import com.laquanquan.personnel_salary.service.ResumeService;
import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;

/**
 * @author lqq
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Resource
    private ResumeMapper resumeMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public WebResponseBody<String> getResumeContent(String id, String token) throws AccessDeniedException {
        // 校验token
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);
        if (user == null) {
            // uid不存在
            throw new AccessDeniedException("不存在该uid，token无效");
        }

        // 校验身份权限
        Role role = roleMapper.selectByRid(user.getRole());
        if (!role.getPersonnelRight()) {
            // 权限校验不通过
            throw new AccessDeniedException("该账户没有权限");
        }

        // 校验通过，返回正确的响应体
        Resume resume = resumeMapper.selectById(user.getResume());
        return new WebResponseBody<>("获取简历成功", resume.getContent());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createResume(String token, String content) throws SQLDataException {
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);
        if (user.getResume() != null) {
            //
            throw new DataDuplicationException("该用户已经存在简历，创建失败");
        }

        user.setResume("rid_" + RandomStringBuilder.build(10));
        if (1 != userMapper.updateOne(user)) {
            // 更新条目不为1，更新失败
            throw new SQLDataException("新建简历失败");
        }

        // 新建简历成功，新建到简历表
        Resume resume = new Resume();
        resume.setRid(user.getResume());
        resume.setContent(content);
        if (resumeMapper.createOne(resume) != 1) {
            // 新增条目不为1，新增简历失败
            throw new SQLDataException("新建简历失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResume(String token, String rid, String content) throws SQLDataException, AccessDeniedException {
        // 校验口令
        String uid = (String) TokenBuilder.parse(token).get("uid");
        User user = userMapper.selectByUid(uid);
        if (user.getResume() == null) {
            // 不存在简历
            throw new DataNotFoundException("该用户不存在简历，无法进行更新");
        }

        if (!rid.equals(user.getResume())) {
            // rid和token身份校验不通过
            throw new AccessDeniedException("身份校验不通过，无法更新简历");
        }

        Resume resume = new Resume();
        resume.setRid(user.getResume());
        resume.setContent(content);

        if (resumeMapper.updateOne(resume) != 1) {
            // 更新条目不正确
            throw new SQLDataException("更新简历失败，请重试！");
        }
    }
}
