package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.service.ResumeService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.ResumeVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;

/**
 * @author lqq
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    @Resource
    private ResumeService resumeService;

    @GetMapping("/{id}")
    public WebResponseBody<String> getContent(@PathVariable String id, @RequestParam String token) throws AccessDeniedException {
        return resumeService.getResumeContent(id, token);
    }

    @PostMapping
    public void create(@RequestBody ResumeVO resumeVO) throws SQLDataException {
        resumeService.createResume(resumeVO.getToken(), resumeVO.getContent());
    }

    @PutMapping
    public void update(@RequestBody ResumeVO resumeVO) throws SQLDataException, AccessDeniedException {
        resumeService.updateResume(resumeVO.getToken(), resumeVO.getRid(), resumeVO.getContent());
    }
}
