package com.example.dayte.schedule.service;

import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.dto.ScheduleDTO;
import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.persistence.ContentsRepository;
import com.example.dayte.schedule.persistence.ScheduleDateRepository;
import com.example.dayte.schedule.persistence.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

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

        ScheduleDate scheduleDate = scheduleDateRepository.findById(userScheduleDTO.getUuid())
                .orElseThrow(() -> new NoSuchElementException("ScheduleDate not found"));

        int[] i = {0};
        userScheduleDTO.getContents().forEach(userSchedule -> {
            LocalDate newDate = scheduleDate.getStartDate().plus(Period.ofDays(userScheduleDTO.getNextDays())).minusDays(1);

            Contents contents = contentsRepository.findById(userSchedule.toString())
                    .orElseThrow(() -> new NoSuchElementException("Contents not found"));

            Schedule schedule = Schedule.builder()
                    .contents(contents)
                    .scheduleDate(scheduleDate)
                    .nowDate(newDate)
                    .sequence(++i[0])
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
