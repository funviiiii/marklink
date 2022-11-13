package com.laquanquan.personnel_salary.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lqq
 */
@Component
@ConfigurationProperties(prefix = "email-sender")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailSender {
    private String from;
    @Resource
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String context) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);
        javaMailSender.send(simpleMailMessage);
    }
}
