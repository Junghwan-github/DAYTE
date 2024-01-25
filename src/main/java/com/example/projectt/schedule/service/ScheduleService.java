package com.example.projectt.schedule.service;

import com.example.projectt.members.domain.User;
import com.example.projectt.schedule.domain.ScheduleDate;
import com.example.projectt.schedule.dto.ScheduleDTO;
import com.example.projectt.security.dto.UserSecurityDTO;
import com.example.projectt.schedule.domain.Contents;
import com.example.projectt.schedule.domain.Schedule;
import com.example.projectt.schedule.persistence.ContentsRepository;
import com.example.projectt.schedule.persistence.ScheduleDateRepository;
import com.example.projectt.schedule.persistence.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleDateRepository scheduleDateRepository;

    @Autowired
    private ScheduleDateService scheduleDateService;

    @Autowired
    private ContentsRepository contentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void insertSchedule(ScheduleDTO userScheduleDTO) {
        System.out.println("userScheduleDTO: " + userScheduleDTO);

        LocalDate createDate = scheduleDateRepository.findById(userScheduleDTO.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("ScheduleDate not found"))
                .getStartDate();


        int[] i = {0};
        userScheduleDTO.getContents().forEach(userSchedule -> {
            LocalDate newDate = createDate.plus(Period.ofDays(userScheduleDTO.getNextDays())).minusDays(1);

            Contents contents = contentsRepository.findById(userSchedule.toString())
                    .orElseThrow(() -> new IllegalArgumentException("Contents not found"));

            ScheduleDate scheduleDate = scheduleDateRepository.findById(userScheduleDTO.getUuid())
                    .orElseThrow(() -> new IllegalArgumentException("ScheduleDate not found"));

            i[0] +=1;
            Schedule schedule = Schedule.builder()
                    .contents(contents)
                    .scheduleDate(scheduleDate)
                    .nowDate(newDate)
                    .sequence(i[0])
                    .build();
            scheduleRepository.save(schedule);
        });
    }

    @Transactional(readOnly = true)
    public List<Contents> getContentsList() {
        return contentsRepository.findAll();
    }

    public List<Schedule> selectSchedule(String uuid) {
        return scheduleRepository.findByUuid(uuid);
    }
}
