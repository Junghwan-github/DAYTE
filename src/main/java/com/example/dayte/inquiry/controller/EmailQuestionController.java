package com.example.dayte.inquiry.controller;

import com.example.dayte.inquiry.dto.EmailQuestionDTO;
import com.example.dayte.inquiry.service.EmailQuestionService;
import com.example.dayte.members.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailQuestionController {

    @Autowired
    private EmailQuestionService emailQuestionService;

    @GetMapping("/customerService")
    public String goToEmailQuestion() {
        return "customer/customer";
    }

    @PostMapping("/question")
    public @ResponseBody ResponseDTO<?> goToEmailQuestion(@ModelAttribute EmailQuestionDTO emailQuestionDTO) {

        String receivedEmail = emailQuestionDTO.getEmailAdress();

        emailQuestionService.sendQuestion(emailQuestionDTO, receivedEmail);

        return new ResponseDTO<>(HttpStatus.OK.value(), "문의하신 내용이 메일로 정상 전송 되었습니다.");
    }

}
