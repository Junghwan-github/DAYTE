package com.example.dayte.emailQuestion.controller;

import com.example.dayte.emailQuestion.domain.EmailQuestion;
import com.example.dayte.emailQuestion.service.EmailQuestionService;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Controller
public class emailQuestionController {

    @Autowired
    private EmailQuestionService emailQuestionService;

    @GetMapping("/question")
    public String goToEmailQuestion(){
        return "emailQuestion/emailQuestion";
    }


@PostMapping("/question")
    public Integer goToEmailQuestion(@RequestBody EmailQuestion emailQuestion,
                                @AuthenticationPrincipal UserSecurityDTO principal){

     //   js에서 이 사람 아이디랑 닉네임도 받아서 보내기

     emailQuestionService.sendQuestion(emailQuestion, principal);

    /*System.out.println(principal.getUserEmail());*/
    System.out.println("^^^^^^^^^^^^^");
    System.out.println(HttpStatus.OK.value());

return HttpStatus.OK.value();
}

}
