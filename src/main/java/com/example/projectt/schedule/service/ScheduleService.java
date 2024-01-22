package com.example.projectt.schedule.service;

import com.example.projectt.members.User;
import com.example.projectt.members.dto.UserSecurityDTO;
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

    public void insertSchedule(String saveSchedule, int nextDays, UserSecurityDTO userSecurityDTO, String uuid) {
        LocalDate createDate = scheduleDateRepository.findById(uuid).get().getStartDate();
        User user = modelMapper.map(userSecurityDTO, User.class);

        String[] saveScheduleArray = saveSchedule.split(",");
        for (int i = 0; i < saveScheduleArray.length; i++) {
            LocalDate newDate = createDate.plus(Period.ofDays(nextDays)).minusDays(1);
            Schedule schedule = Schedule.builder()
                    .user(user)
                    .contents(contentsRepository.findById(saveScheduleArray[i]).get())
                    .scheduleDate(scheduleDateRepository.findById(uuid).get())
                    .nowDate(newDate)
                    .sequence(i)
                    .build();
            scheduleRepository.save(schedule);
        }
    }

    @Transactional(readOnly = true)
    public List<Contents> getContentsList() {
        return contentsRepository.findAll();
    }

    public List<Schedule> selectSchedule(String uuid) {
        return scheduleRepository.findByUuid(uuid);
    }
}
