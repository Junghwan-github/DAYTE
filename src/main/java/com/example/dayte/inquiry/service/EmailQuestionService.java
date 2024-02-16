package com.example.dayte.inquiry.service;

import com.example.dayte.inquiry.domain.EmailQuestion;
import com.example.dayte.inquiry.dto.EmailQuestionDTO;
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


    public void sendQuestion(EmailQuestionDTO emailQuestionDTO, String emailAdress) {

        MimeMessage message = javaMailSender.createMimeMessage();

        try { //유저에게 메일 보냄
            message.setRecipients(MimeMessage.RecipientType.TO, emailAdress);
            message.setSubject("문의하신 답변이 접수되었습니다.");
            String content = "";
            content += "<h2>" + emailQuestionDTO.getTitle() + "</h2>";
            content += "<h1>" + emailQuestionDTO.getContent() + "</h1>";
            content += "<h2>에 대한 문의가 접수되었습니다. 감사합니다.</h2>";
            message.setText(content, "UTF-8", "html");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            ;
            ;

        }


        try { //관리자한테 메일 보냄
            message.setRecipients(MimeMessage.RecipientType.TO, adminEmail);
            InternetAddress[] replyTo = {new InternetAddress(emailAdress)};
            message.setReplyTo(replyTo);
            message.setSubject(emailAdress + "님의 문의 내용");

            String emailContent =
                    "제목 : " + emailQuestionDTO.getTitle() + "\n"
                    + "메인 분류 : " + emailQuestionDTO.getMainCategory() + "\n"
                    + "서브 분류 : " + emailQuestionDTO.getSubCategory() + "\n"
                    + "내용 : " + emailQuestionDTO.getContent() + "\n"
                    +"=======================================" +"\n"
                    +"담당자는 위 내용 빠르게 답변 부탁드립니다. 답장 버튼을 누르면 해당 유저에게 바로 답장 가능합니다.";

            message.setText(emailContent);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            ;
            ;

        }


        System.out.println(adminEmail);


    }
}
