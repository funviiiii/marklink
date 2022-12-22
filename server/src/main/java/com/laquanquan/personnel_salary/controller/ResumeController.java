package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Resume;
import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.mapper.ResumeMapper;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lqq
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    @Resource
    private ResumeMapper resumeMapper;

    @GetMapping("/{id}")
    public WebResponseBody<String> getContent(@PathVariable String id) {
        Resume resume = resumeMapper.selectById(id);
        if (resume == null) {
            throw new DataNotFoundException("不存在id为" + id + "的简历");
        }
        return new WebResponseBody<>("获取简历成功", resume.getContent());
    }
}
