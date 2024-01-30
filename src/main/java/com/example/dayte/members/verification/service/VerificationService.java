package com.example.dayte.members.verification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class VerificationService {

    private int randomNumber;

    private int verifyNumber;

    @Autowired
    JavaMailSender javaMailSender;


    public void makeVerifyNumber() {
        Random rn = new Random();
        int checkNum = rn.nextInt(888888) + 111111;
        randomNumber = checkNum;
    }


    public int sendEmail(String email) {
        makeVerifyNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("회원 가입 인증번호");
            String content = "";
            content += "<h2>요청하신 인증 번호입니다.</h2>";
            content += "<h1>" + randomNumber + "<h1>";
            content += "<h2>감사합니다</h2>";
            message.setText(content, "UTF-8", "html");
            javaMailSender.send(message);
            verifyNumber =  randomNumber;
        } catch (MessagingException e) {
            ;
            ;
        }
        return verifyNumber;
    }

    public int verifyNumberCheck(int emailNumber, int verifyValueNumber) {
        if(emailNumber == verifyValueNumber) {
            return HttpStatus.OK.value();
        }
        return HttpStatus.BAD_REQUEST.value();
    }
}



