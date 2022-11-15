package com.laquanquan.personnel_salary.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lqq
 */
@Configuration
public class ObjectMapperBean {

    @Bean
    public ObjectMapper get() {
        return new ObjectMapper();
    }
}
