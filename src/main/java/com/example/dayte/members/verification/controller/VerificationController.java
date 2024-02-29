package com.example.dayte.members.verification.controller;


import com.example.dayte.members.verification.dto.VerificationDTO;
import com.example.dayte.members.verification.service.VerificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class VerificationController {

    private int verifyValueNumber;

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/members/sendEmail")
    public void verificationEmail (@RequestBody VerificationDTO verificationDTO) {
        this.verifyValueNumber = verificationService.sendEmail(verificationDTO.getAddress());
    }

    @PostMapping("/members/checkEmail")
    @ResponseBody
    public int checkingEmail (@RequestBody int emailNum) {
       return verificationService.verifyNumberCheck(emailNum, verifyValueNumber);
    }
}
