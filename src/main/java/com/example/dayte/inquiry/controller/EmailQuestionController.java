package com.example.dayte.inquiry.controller;

import com.example.dayte.inquiry.domain.EmailQuestion;
import com.example.dayte.inquiry.service.EmailQuestionService;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class EmailQuestionController {

    @Autowired
    private EmailQuestionService emailQuestionService;

    @GetMapping("/customerService")
    public String goToEmailQuestion() {
        return "customer/customer";
    }


    @PostMapping("/question")
    public void goToEmailQuestion(@RequestBody EmailQuestion emailQuestion,
                                                          @AuthenticationPrincipal UserSecurityDTO principal) {

        if(emailQuestion.getEmailAdress() == null){
            System.out.println("@@@@@@@@@@@@@@@");
            emailQuestionService.sendQuestion(emailQuestion, principal.getUserEmail());
        } else{
            emailQuestionService.sendQuestion(emailQuestion, emailQuestion.getEmailAdress());
        }



        //   js에서 이 사람 아이디랑 닉네임도 받아서 보내기


    }

}
