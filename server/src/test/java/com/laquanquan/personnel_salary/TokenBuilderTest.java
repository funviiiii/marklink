package com.laquanquan.personnel_salary;

import com.laquanquan.personnel_salary.utils.RandomStringBuilder;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lqq
 */
@SpringBootTest
public class TokenBuilderTest {

    private static String token;
    private static String uid;

    @BeforeAll
    static void init() {
        uid = RandomStringBuilder.build(10);
        Map<String,Object> payload = new HashMap<>();
        payload.put("uid", uid);
        token = TokenBuilder.build(payload, 1);
    }

    @Test
    void getUid() {
        System.out.println(token);
        Assertions.assertEquals(uid, TokenBuilder.parse(token).get("uid"));
    }
}
