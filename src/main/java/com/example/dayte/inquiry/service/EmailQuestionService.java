package com.example.dayte.inquiry.service;

import com.example.dayte.inquiry.domain.EmailQuestion;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailQuestionService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;




    public void sendQuestion(EmailQuestion emailQuestion, UserSecurityDTO principal) {

        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            message.setRecipients(MimeMessage.RecipientType.TO, principal.getUserEmail());
            message.setSubject("문의하신 답변이 접수되었습니다.");
            String content = "";
            content += "<h2>"+emailQuestion.getTitle()+"</h2>";
            content += "<h1>"+emailQuestion.getContent()+"</h1>";
            content += "<h2>에 대한 문의가 접수되었습니다. 감사합니다.</h2>";
            message.setText(content, "UTF-8", "html");
            javaMailSender.send(message);
        }catch (MessagingException e){
            ;;
        }


        try{
            message.setRecipients(MimeMessage.RecipientType.TO, adminEmail);
            InternetAddress[] replyTo = {new InternetAddress(principal.getUserEmail())};
            message.setReplyTo(replyTo);
            message.setSubject(emailQuestion.getTitle());
            message.setText(emailQuestion.getContent());
            javaMailSender.send(message);

        } catch (MessagingException e){
            ;;
        }




        System.out.println(adminEmail);

        /*try{
            message.setRecipients(Message.RecipientType.TO, );
        }*/




    }
}
