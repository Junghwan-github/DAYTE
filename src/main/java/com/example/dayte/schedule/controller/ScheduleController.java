package com.example.dayte.schedule.controller;


import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.dto.CheckScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDateDTO;
import com.example.dayte.schedule.service.ScheduleDateService;
import com.example.dayte.schedule.service.ScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ScheduleDateService scheduleDateService;

    private final AdminContentsService adminContentsService;

    // 사용자가 계획한 일정 전체를 보여주는 로직
    @GetMapping("/schedule/scheduleList")
    public String moveScheduleList(Model model,
                                   @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userScheduleList",
                        scheduleService.selectScheduleByUser(userSecurityDTO))
                .addAttribute("contentsList", adminContentsService.getContentsList())
                .addAttribute("dDay", LocalDate.now().toEpochDay());
        return "scheduleList/scheduleList";
    }

    // 사용자가 선택한 날짜에 맞춰 일정이 생성되는 로직
    @PostMapping("/schedule/scheduleList")
    public @ResponseBody CheckScheduleDTO<?> insertScheduleList(
            @RequestBody ScheduleDTO scheduleDTO,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        CheckScheduleDTO checkScheduleDTO = scheduleService.getUserSchedule(scheduleDTO, userSecurityDTO);
        if (checkScheduleDTO.getOverlap()) {
            checkScheduleDTO.setStatus(HttpStatus.CONFLICT.value());
            checkScheduleDTO.setData("일정이 이미 있습니다.");
            return checkScheduleDTO;
        } else {
            scheduleService.insertSchedule(userSecurityDTO, scheduleDTO);
            return new CheckScheduleDTO<>(HttpStatus.OK.value(), "일정이 등록 되었습니다.");
        }
    }

    // 사용자가 만든 일정의 세부계획을 생성하는 로직
    @PostMapping("/schedule/saveSchedule")
    public @ResponseBody ResponseDTO<?> saveScheduleList(@RequestBody ScheduleDateDTO scheduleDateDTO) {
        scheduleDateService.insertSchedule(scheduleDateDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 등록 되었습니다.");
    }

    // 사용자의 일정 전체를 삭제하는 로직
    @DeleteMapping("schedule/scheduleList/{startDate}")
    public void deleteSchedule(@PathVariable LocalDate startDate,
                               @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        scheduleService.deleteSchedule(startDate, userSecurityDTO);
    }

    // 사용자가 일정이 이미 있다면 삭제후 재등록 하는 로직
    @DeleteMapping("/schedule/deleteAndInsertSchedule")
    public @ResponseBody ResponseDTO<?> deleteAndInsertSchedule(
            @RequestBody ScheduleDTO scheduleDTO,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        try {
            scheduleService.detailedDeleteSchedule(scheduleDTO.getUuid());

            // 새로운 일정 등록
            scheduleService.insertSchedule(userSecurityDTO, scheduleDTO);
            return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 삭제되었고 새로운 일정이 등록되었습니다.");
        } catch (Exception e) {
            return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "일정 삭제 및 등록 중 오류가 발생했습니다.");
        }
    }
    @Transactional
    @PutMapping("/schedule/detailedScheduleModify")
    public @ResponseBody ResponseDTO<?> detailedScheduleModify(
            @RequestBody ScheduleDateDTO scheduleDateDTO
    ){
        scheduleDateService.updateSchedule(scheduleDateDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "일정이 수정 되었습니다.");
    }
}

