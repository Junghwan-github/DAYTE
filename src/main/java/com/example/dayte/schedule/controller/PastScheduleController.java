package com.example.dayte.schedule.controller;

import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.service.PastScheduleService;
import com.example.dayte.schedule.service.ScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PastScheduleController {


    private final ScheduleService scheduleService;
    private final PastScheduleService pastScheduleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/schedule/pastSchedule")
    public String pastSchedule(Model model, @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        System.out.println("^^^^^^^^^^^^^^^^^^^");
        System.out.println("스케쥴 리스트 : " + pastScheduleService.selectPastScheduleByUser(userSecurityDTO));

        List<Schedule> pastScheduleList = pastScheduleService.selectPastScheduleByUser(userSecurityDTO);

        if (pastScheduleList.isEmpty()) {
            System.out.println("아무것도 없음");
        } else {
            model.addAttribute("pastScheduleList", pastScheduleList);
        }

        return "scheduleList/pastSchedule";


    }

}
