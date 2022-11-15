package com.laquanquan.personnel_salary.controller;

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
        RequestBuilder builder = MockMvcRequestBuilders.get("/email/1069227225@qq.com")
                .contentType("text/plain;charset=utf-8");
        ResultActions perform = mockMvc.perform(builder);

        ResultMatcher matcher = MockMvcResultMatchers.status().isCreated();
        perform.andExpect(matcher);
    }
}