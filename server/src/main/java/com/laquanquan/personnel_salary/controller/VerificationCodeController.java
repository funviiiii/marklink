package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.constant.RedisKey;
import com.laquanquan.personnel_salary.constant.RegPattern;
import com.laquanquan.personnel_salary.exception.UserInfoInvalidException;
import com.laquanquan.personnel_salary.utils.EmailSender;
import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lqq
 */
@RestController
@RequestMapping(value = "/email", produces = "application/json;charset=utf-8")
public class VerificationCodeController {

    @Resource
    private EmailSender emailSender;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String SUBJECT = "人事工资管理系统";

    private static final String CONTENT_PREFIX = "您的邮箱验证码为：\n";

    private static final String CONTENT_SUFFIX = "\n验证码将在5分钟后失效";


    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public WebResponseBody<String> getVerificationCode(@RequestParam String email) {
        // 校验邮箱地址正确性
        if (!RegPattern.EMAIL_REG.matcher(email).find()) {
            throw new UserInfoInvalidException("邮箱地址格式不正确");
        }


        // 将验证码放入Redis存储
        // 如果查询到的code为空说明该用户没请求过验证码，需要先生成再发送给用户
        String verificationCode = stringRedisTemplate.opsForValue().get(RedisKey.VERIFICATION_CODE_KEY + email);
        if (verificationCode == null) {
            // 创建验证码
            verificationCode = RandomStringBuilder.buildInteger(6);
            stringRedisTemplate.opsForValue()
                    .set(RedisKey.VERIFICATION_CODE_KEY + email, verificationCode, 6, TimeUnit.MINUTES);
        }
        // 发送验证码
        emailSender.send(email, SUBJECT, CONTENT_PREFIX + verificationCode + CONTENT_SUFFIX);


        return new WebResponseBody<>("验证码发送成功");
    }
}
