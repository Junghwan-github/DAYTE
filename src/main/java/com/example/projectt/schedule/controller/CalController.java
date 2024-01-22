package com.example.projectt.schedule.controller;

import com.example.projectt.security.dto.UserSecurityDTO;
import com.example.projectt.schedule.dto.ScheduleDateDTO;
import com.example.projectt.schedule.service.ScheduleDateService;
import com.example.projectt.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CalController {

    @Autowired
    private ScheduleService contentsService;

    @Autowired
    private ScheduleDateService scheduleDateService;

    @GetMapping("/schedule/scheduleList/{uuid}")
    public String getScheduleList(
            @PathVariable String uuid,
            Model model) {
        model.addAttribute("ScheduleDate", scheduleDateService.selectScheduleDate(uuid));
        return "scheduleList/memberSchedule";
    }

    @GetMapping("/schedule/scheduleList")
    public String moveScheduleList(Model model,
    @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {

        model.addAttribute("selectScheduleDate", scheduleDateService.selectScheduleByUser(userSecurityDTO));
        return "scheduleList/scheduleList";
    }

    @PostMapping("/schedule/scheduleList")
    public @ResponseBody Map<String, String> insertScheduleList(
            @RequestBody ScheduleDateDTO scheduleDTO,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        Map<String, String> response = new HashMap<>();
        scheduleDateService.insertScheduleDate(userSecurityDTO, scheduleDTO);
        response.put("status", "success");
        response.put("message", "일정이 성공적으로 추가되었습니다.");
        response.put("uuid", scheduleDateService.findScheduleByUserAndStartDate(userSecurityDTO, scheduleDTO).getUuid());
        return response;
    }

    @GetMapping("/schedule/map/{uuid}")
    public String insertSchedule(@RequestParam(name = "nextDays", defaultValue = "0") int nextDays,
                                 @PathVariable String uuid,
                                 Model model) {
        model.addAttribute("nextDays", nextDays)
                .addAttribute("contentsList", contentsService.getContentsList())
                .addAttribute("uuid", uuid);
        return "scheduleList/map";
    }

    @GetMapping("/schedule/scheduleLists/{uuid}")
    public String saveSchedule(@RequestParam(name = "saveSchedule", required = false) String saveSchedule,
                               @RequestParam(name = "nextDays", required = false) int nextDays,
                               @PathVariable String uuid,
                               @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
                               Model model) {

        contentsService.insertSchedule(saveSchedule, nextDays, userSecurityDTO, uuid);

        model.addAttribute("nextDay", nextDays)
                    .addAttribute("ScheduleDate", scheduleDateService.selectScheduleDate(uuid))
                    .addAttribute("saveSchedule", contentsService.selectSchedule(uuid));

    return "scheduleList/memberSchedule";
    }
}
