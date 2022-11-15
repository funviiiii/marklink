package com.laquanquan.personnel_salary.aop;

import com.laquanquan.personnel_salary.exception.DataNotFoundException;
import com.laquanquan.personnel_salary.utils.EmailSender;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author lqq
 */
@RestControllerAdvice(basePackages = {"com.laquanquan.personnel_salary.controller"})
@Slf4j
public class ExceptionAdvice {
    @Resource
    private EmailSender emailSender;

    private static final String ERR_MSG_CONTENT = "ERROR!!\n人事管理系统在" +
            LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + "发生了异常:\n";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebResponseBody<String> allException(Exception e) {
        emailSender.send("1069227225@qq.com", "未知的系统异常", ERR_MSG_CONTENT + e.getMessage());
        log.error("发生了未知的系统异常", e);
        return new WebResponseBody<>("发生了未知的错误，请稍后重试");
    }

    @ExceptionHandler({DataNotFoundException.class, SQLDataException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public WebResponseBody<String> dataNotFound(DataNotFoundException e) {
        log.warn(e.getMessage(), e);
        return new WebResponseBody<>(e.getMessage());
    }
}
