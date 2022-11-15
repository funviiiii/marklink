package com.laquanquan.personnel_salary.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class VerificationCodeControllerTest {

    @Resource
    private MockMvc mockMvc;

    /**
     * 测试是否能正确获取验证码
     */
    @Test
    void getVerificationCode() throws Exception {
        long start = System.currentTimeMillis();
        RequestBuilder builder = MockMvcRequestBuilders.get("/email/1069227225@qq.com")
                .contentType("text/plain;charset=utf-8");
        ResultActions perform = mockMvc.perform(builder);

        ResultMatcher matcher = MockMvcResultMatchers.status().isCreated();
        perform.andExpect(matcher);

        long rs = System.currentTimeMillis() - start;

        // 保证在5秒内完成请求的返回，而不会因为邮件发送和Redis存储的原因阻塞
        Assertions.assertTrue(rs < 5 * 1000);

        // 避免由于主线程关闭导致线程池的线程死亡
        Thread.sleep(1000 * 20);
    }
}