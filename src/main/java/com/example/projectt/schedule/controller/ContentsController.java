package com.example.projectt.schedule.controller;

import com.example.projectt.members.dto.ResponseDTO;
import com.nimbusds.oauth2.sdk.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ContentsController {

    @PostMapping("/schedule/map")
    public String selectSchedule(@RequestBody String userSchedule
            , Model model) {
        return  userSchedule;
    }

    @GetMapping("/schedule/map")
    public String showSchedule(Model model, String userSchedule) {
        model.addAttribute("userSchedule", userSchedule);
        return "scheduleList/map";
    }
}
