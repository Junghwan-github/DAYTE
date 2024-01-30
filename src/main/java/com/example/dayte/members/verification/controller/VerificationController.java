package com.example.dayte.members.verification.controller;


import com.example.dayte.members.verification.dto.VerificationDTO;
import com.example.dayte.members.verification.service.VerificationService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    private int verifyValueNumber;


    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/members/sendEmail")
    @ResponseBody
    public int verificationEmail (@RequestBody VerificationDTO verificationDTO) {

        this.verifyValueNumber = verificationService.sendEmail(verificationDTO.getAddress());
        log.info("======== VerificationDTO : " + verificationDTO);
        return HttpStatus.OK.value();
    }

    @PostMapping("/members/checkEmail")
    @ResponseBody
    public int checkingEmail (@RequestBody int emailNum) {

       return verificationService.verifyNumberCheck(emailNum, verifyValueNumber);
    }
}
