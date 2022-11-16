package com.laquanquan.personnel_salary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.mapper.AccountMapper;
import com.laquanquan.personnel_salary.utils.Md5Utils;
import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lqq
 */
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class AccountControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    private static Account account;

    private static String realPassword;

    @BeforeAll
    static void init(@Autowired AccountMapper accountMapper) {
        Account account = new Account();
        account.setUid(RandomStringBuilder.build(10));
        account.setUsername(RandomStringBuilder.build(10));
        realPassword = RandomStringBuilder.build(10);
        account.setPassword(Md5Utils.encode(realPassword));
        account.setEmail(RandomStringBuilder.build(10) + "@qq.com");
        account.setPhone(RandomStringBuilder.buildInteger(11));
        AccountControllerTest.account = account;
        accountMapper.saveOne(account);
    }

    @Test
    void getAccount() throws Exception {
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/account")
                .contentType("application/json;charset=utf-8").content("{\"uid\": \"" + account.getUid() + "\"}");
        ResultActions perform = mockMvc.perform(reqBuilder);

        ResultMatcher matcher1 = MockMvcResultMatchers.content().string(account.getUid());
        ResultMatcher matcher2 = MockMvcResultMatchers.status().is(HttpStatus.OK.value());
        perform.andExpect(matcher1).andExpect(matcher2);
        perform.andExpect(matcher1).andExpect(matcher2);
    }

    @Test
    void signUp() throws Exception {
        Account newAccount = new Account();

        newAccount.setUsername(RandomStringBuilder.build(10));
        newAccount.setPassword(RandomStringBuilder.build(10));
        newAccount.setEmail(RandomStringBuilder.build(10) + "@qq.com");
        newAccount.setPhone(RandomStringBuilder.buildInteger(11));

        Map<String, Object> obj = new HashMap<>();
        obj.put("username", newAccount.getUsername());
        obj.put("password", newAccount.getPassword());
        obj.put("email", newAccount.getEmail());
        obj.put("phone", newAccount.getPhone());
        obj.put("name", RandomStringBuilder.build(5));
        obj.put("gender", "男");
        obj.put("birthday", new Date());
        obj.put("is_married", 0);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account")
                .contentType("application/json;charset=utf-8").content(objectMapper.writeValueAsString(obj));

        ResultActions perform = mockMvc.perform(requestBuilder);

        ResultMatcher matcher = MockMvcResultMatchers.status().is(HttpStatus.CREATED.value());
        perform.andExpect(matcher);
    }

    @Test
    void signIn() throws Exception {
        // 初始化数据
        Account tmp = new Account();
        // 1. 账号不正确
        tmp.setUsername(RandomStringBuilder.build(10));
        tmp.setPassword(RandomStringBuilder.build(10));
        RequestBuilder builder1 = MockMvcRequestBuilders.post("/account/signin")
                .contentType("application/json;charset=utf-8").content(objectMapper.writeValueAsString(tmp));
        ResultActions perform1 = mockMvc.perform(builder1);
        ResultMatcher status1 = MockMvcResultMatchers.status().isNotFound();
        ResultMatcher result1 = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(new WebResponseBody<String>("不存在该用户，请检查用户名是否正确!")));
        perform1.andExpect(status1).andExpect(result1);

        // 2. 密码不正确
        tmp.setUsername(account.getUsername());
        RequestBuilder builder2 = MockMvcRequestBuilders.post("/account/signin")
                .contentType("application/json;charset=utf-8").content(objectMapper.writeValueAsString(tmp));
        ResultActions perform2 = mockMvc.perform(builder2);
        ResultMatcher status2 = MockMvcResultMatchers.status().isNotFound();
        ResultMatcher result2 = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(new WebResponseBody<String>("密码错误！请重试！")));
        perform2.andExpect(status2).andExpect(result2);
        // 3. 账号密码都正确
        tmp.setPassword(realPassword);
        RequestBuilder builder3 = MockMvcRequestBuilders.post("/account/signin")
                .contentType("application/json;charset=utf-8").content(objectMapper.writeValueAsString(tmp));
        ResultActions perform3 = mockMvc.perform(builder3);
        ResultMatcher status3 = MockMvcResultMatchers.status().isOk();
        perform3.andExpect(status3);
    }

    @AfterAll
    static void clear(@Autowired AccountMapper accountMapper) throws SQLDataException {
        if (accountMapper.hardDeleteByUid(account.getUid()) != 1) {
            throw new SQLDataException("清理测试数据失败");
        }
    }
}
