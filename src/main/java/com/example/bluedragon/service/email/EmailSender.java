package com.example.bluedragon.service.email;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //기본적으로 구글 SMTP 서버를 이용한다.
    //본인의 계정으로 다른 이용자에게 메일을 보내는 개념이다.
    //본인의 구글계정 아이디/앱비밀번호 정보를 application.yml에 정의한다.

    /**
     * @param toEmailAddr 받는사람이메일주소
     * @param subject     제목
     * @param text        내용
     * @methodName : sendEmailToMember
     * @description : 메일전송
     * @author :
     * @date : 2023.10.07
     */
    public void sendEmailToMember(String toEmailAddr, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        try {
            message.setTo(toEmailAddr);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
