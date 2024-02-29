package com.example.dayte.inquiry.service;

import com.example.dayte.inquiry.domain.EmailQuestion;
import com.example.dayte.inquiry.dto.EmailQuestionDTO;
import com.example.dayte.inquiry.persistence.EmailQuestionRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class EmailQuestionService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailQuestionRepository emailQuestionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${spring.mail.username}")
    private String adminEmail;

    public void sendQuestion(EmailQuestionDTO emailQuestionDTO, String emailAdress) {

        try { //유저에게 메일 보냄
            MimeMessage message = javaMailSender.createMimeMessage();

            message.setRecipients(MimeMessage.RecipientType.TO, emailAdress);
            message.setSubject("문의하신 답변이 접수되었습니다.");
            String content = "";
            content += "<h2> 제목 : " + emailQuestionDTO.getTitle() + "</h2>";
            content += "<h1> 문의 내용 : " + emailQuestionDTO.getContent() + "</h1>";
            content += "<h2>에 대한 문의가 접수되었습니다. 감사합니다.</h2>";
            message.setText(content, "UTF-8", "html");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            ;
            ;
        }


        try { // 관리자에게 메일 보냄
            MimeMessage adminMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper adminMessageHelper = new MimeMessageHelper(adminMessage, true, "UTF-8");

            adminMessageHelper.setTo(adminEmail);
            adminMessageHelper.setReplyTo(emailAdress);
            adminMessageHelper.setSubject(emailAdress + "님의 문의 내용");

            String emailContent =
                    "제목 : " + emailQuestionDTO.getTitle() + "\n"
                            + "메인 분류 : " + emailQuestionDTO.getMainCategory() + "\n"
                            + "서브 분류 : " + emailQuestionDTO.getSubCategory() + "\n"
                            + "내용 : " + emailQuestionDTO.getContent() + "\n"
                            + "=======================================" + "\n"
                            + "담당자는 위 내용 빠르게 답변 부탁드립니다. 답장 버튼을 누르면 해당 유저에게 바로 답장 가능합니다.";

            adminMessageHelper.setText(emailContent);

            // 파일 첨부 (관리자에게만)
            if (emailQuestionDTO.getFiles() != null && emailQuestionDTO.getFiles().size() > 0) {
                for (MultipartFile file : emailQuestionDTO.getFiles()) {
                    adminMessageHelper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
                }
            }

            javaMailSender.send(adminMessage);

            emailQuestionRepository.save(modelMapper.map(emailQuestionDTO, EmailQuestion.class));

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
