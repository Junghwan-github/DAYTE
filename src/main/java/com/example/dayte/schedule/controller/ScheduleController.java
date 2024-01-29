package com.example.dayte.schedule.controller;


import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.dto.ScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDateDTO;
import com.example.dayte.schedule.service.ContentsService;
import com.example.dayte.schedule.service.ScheduleDateService;
import com.example.dayte.schedule.service.ScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ScheduleDateService scheduleDateService;

    private final ContentsService contentsService;

    // 사용자가 계획한 일정 전체를 보여주는 로직
    @GetMapping("/schedule/scheduleList")
    public String moveScheduleList(Model model,
                                   @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userScheduleList",
                        scheduleService.selectScheduleByUser(userSecurityDTO))
                .addAttribute("contentsList", contentsService.getContentsList())
                .addAttribute("dDay", LocalDate.now().toEpochDay());
        return "scheduleList/scheduleList";
    }

    // 사용자가 선택한 날짜에 맞춰 일정이 생성되는 로직
    @PostMapping("/schedule/scheduleList")
    public @ResponseBody ResponseDTO<?> insertScheduleList(
            @RequestBody ScheduleDTO scheduleDTO,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        Schedule findSchedule = scheduleService.getUserSchedule(scheduleDTO, userSecurityDTO);
        if (findSchedule.getStartDate() == null) {
            scheduleService.insertSchedule(userSecurityDTO, scheduleDTO);
            return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 등록 되었습니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "일정이 이미 있습니다.");
        }
    }

    // 사용자가 만든 일정의 세부계획을 생성하는 로직
    @PostMapping("/schedule/saveSchedule")
    public @ResponseBody ResponseDTO<?> saveScheduleList(@RequestBody ScheduleDateDTO userSchedule) {
        scheduleDateService.insertSchedule(userSchedule);
        return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 등록 되었습니다.");
    }

    // 사용자의 일정 전체를 삭제하는 로직
    @DeleteMapping("schedule/scheduleList/{startDate}")
    public void deleteSchedule(@PathVariable LocalDate startDate,
                               @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        scheduleService.deleteSchedule(startDate, userSecurityDTO);
    }
}

