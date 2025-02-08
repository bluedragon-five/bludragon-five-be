package com.example.bluedragon.service.email;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    void sendEmailToMember() {
        String sendEmail = "jameskkwoo1021@gmail.com";
        String subject = "testEmail";
        String content = "testContent";
        emailSender.sendEmailToMember(sendEmail, subject, content);
    }
}
