package com.example.dayte.schedule.controller;

import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.service.PastScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PastScheduleController {

    private final PastScheduleService pastScheduleService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/pastSchedule")
    public String pastSchedule(Model model, @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {

        List<Schedule> pastScheduleList = pastScheduleService.selectPastScheduleByUser(userSecurityDTO);

        if (!pastScheduleList.isEmpty()) {
            model.addAttribute("pastScheduleList", pastScheduleList);
        }
        return "scheduleList/pastSchedule";
    }
}
