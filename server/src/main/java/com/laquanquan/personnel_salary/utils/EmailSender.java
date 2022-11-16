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
import java.util.concurrent.ExecutorService;

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

    @Resource(name = "EmailExecutor")
    private ExecutorService executorService;

    public void send(String to, String subject, String context) {
        // 使用线程池，避免由于发送邮件的阻塞导致系统卡顿
        executorService.execute(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(context);
            javaMailSender.send(simpleMailMessage);
        });
    }
}
