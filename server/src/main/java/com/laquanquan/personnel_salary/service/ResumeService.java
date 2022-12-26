package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.utils.WebResponseBody;

import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;

/**
 * @author lqq
 */
public interface ResumeService {
    /**
     * 根据简历的编号获取简历
     *
     * @param id    简历的编号
     * @param token 用于身份校验的token
     * @return 返回的响应体
     * @throws AccessDeniedException 当权限不足时，抛出该异常
     */
    WebResponseBody<String> getResumeContent(String id, String token) throws AccessDeniedException;

    /**
     * 根据口令和主体创建简历
     *
     * @param token   用户的口令
     * @param content 简历的主体
     * @throws SQLDataException 当创建简历发生SQL异常时，抛出该异常
     */
    void createResume(String token, String content) throws SQLDataException;

    /**
     * 根据简历编号更新简历
     *
     * @param token   用于身份校验的口令
     * @param rid     简历的编号
     * @param content 简历的主体
     * @throws SQLDataException      当创建简历发生SQL异常时，抛出该异常
     * @throws AccessDeniedException 当身份校验不通过时，抛出该异常
     */
    void updateResume(String token, String rid, String content) throws SQLDataException, AccessDeniedException;
}
