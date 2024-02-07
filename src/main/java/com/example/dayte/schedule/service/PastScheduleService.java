package com.example.dayte.schedule.service;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.persistence.ScheduleRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Schedule> selectPastScheduleByUser(UserSecurityDTO userSecurityDTO) {

        User user = modelMapper.map(userSecurityDTO, User.class);
        List<Schedule> pastScheduleList = scheduleRepository.findAllPastScheduleByUserid(user.getUserEmail());

        return pastScheduleList;
    }
}
