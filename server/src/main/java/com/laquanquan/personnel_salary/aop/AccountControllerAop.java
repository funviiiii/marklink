package com.laquanquan.personnel_salary.aop;

import com.laquanquan.personnel_salary.controller.AccountController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author lqq
 */
@RestControllerAdvice(basePackages = "com.laquanquan.personnel_salary.controller")
@Slf4j
public class AccountControllerAop {
    @Pointcut(value = "execution(* *.*.*.*.*.*(..))")
    public void allController(){}

    @Before("allController()")
    public void visitorLogger() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = Objects.requireNonNull(sra).getRequest();

        log.info("{} : 访问了 {}", request.getRemoteHost(), request.getRequestURI());
    }
}
