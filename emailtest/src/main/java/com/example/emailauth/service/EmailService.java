package com.example.emailauth.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private String fromAddress;

    @PostConstruct
    public void init() {
        // 네이버 계정 그대로
        this.fromAddress = "your_naver_id@naver.com";
    }

    public void sendVerificationEmail(String to, String token) {
        String subject = "이메일 인증 요청";
        String verificationLink = "http://localhost:8080/verify?token=" + token;

        String text = "안녕하세요!\n\n"
                + "이메일 인증을 위해 아래 링크를 클릭해주세요:\n\n"
                + verificationLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(fromAddress); // ✅ 추가
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
        System.out.println("✅ 이메일 전송 완료: " + to);
    }
}
