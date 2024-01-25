package com.example.projectt.schedule.controller;


import com.example.projectt.members.dto.ResponseDTO;
import com.example.projectt.schedule.domain.ScheduleDate;
import com.example.projectt.schedule.dto.ScheduleDateDTO;
import com.example.projectt.schedule.service.ScheduleDateService;
import com.example.projectt.security.dto.UserSecurityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class CalController {

    @Autowired
    private ScheduleDateService scheduleDateService;

    @GetMapping("/schedule/scheduleList")
    public String moveScheduleList(Model model,
                                   @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {

        model.addAttribute("userScheduleList", scheduleDateService.selectScheduleByUser(userSecurityDTO))
                .addAttribute("dDay", LocalDate.now().toEpochDay());
        return "scheduleList/scheduleList";
    }

    @PostMapping("/schedule/scheduleList")
    public @ResponseBody ResponseDTO<?> insertScheduleList(
            @RequestBody ScheduleDateDTO scheduleDTO,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        ScheduleDate findSchedule = scheduleDateService.getUserScheduleDate(scheduleDTO, userSecurityDTO);
        if (findSchedule.getStartDate() == null) {
            scheduleDateService.insertScheduleDate(userSecurityDTO, scheduleDTO);
            return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 등록 되었습니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "일정이 이미 있습니다.");
        }
    }

    @DeleteMapping("/schedule/scheduleList/{startDate}")
    public void deleteSchedule(@PathVariable LocalDate startDate,
                               @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        scheduleDateService.deleteSchedule(startDate, userSecurityDTO);
    }
}
