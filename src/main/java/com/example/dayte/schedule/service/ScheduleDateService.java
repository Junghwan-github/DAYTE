package com.example.dayte.schedule.service;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.*;
import com.example.dayte.schedule.dto.DetailedScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDateDTO;
import com.example.dayte.schedule.persistence.ContentsRepository;
import com.example.dayte.schedule.persistence.DetailedScheduleRepository;
import com.example.dayte.schedule.persistence.ScheduleDateRepository;
import com.example.dayte.schedule.persistence.ScheduleRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleDateService {

    private final ScheduleDateRepository scheduleDateRepository;

    private final ScheduleRepository scheduleRepository;

    private final ContentsRepository contentsRepository;

    private final ModelMapper modelMapper;

    private final DetailedScheduleRepository detailedScheduleRepository;

    @Transactional
    public void insertSchedule(ScheduleDateDTO scheduleDateDTO) {
        Schedule schedule =
                scheduleRepository.findById(scheduleDateDTO.getUuid())
                        .orElseThrow(() -> new NoSuchElementException("Schedule not found"));

        // Setter 로 세팅하는 이유 : 기본생성자와 setter 밖에 만들지 못함
        // (필드값 다 받는 일반생성자와 Builder 어노테이션도 달아봤지만 에러 발생함)
        ScheduleDateId scheduleDateId = new ScheduleDateId();
        DetailedScheduleDTO detailedScheduleDTO = new DetailedScheduleDTO();

        scheduleDateId.setSchedule(schedule); // schedule 객체 셋팅
        scheduleDateId.setNowDate(scheduleDateDTO.getNowDate()); // nowDate 셋팅

        detailedScheduleDTO.setScheduleDate(scheduleDateRepository.findById(scheduleDateId).get());

        scheduleDateDTO.setScheduleDateId(scheduleDateId); // scheduleDateDTO 에 scheduleDateId 셋팅

        List<DetailedSchedule> detailedScheduleList = new ArrayList<>();
        for (Contents contents : contentsRepository.findAllById(scheduleDateDTO.getContentsList())) {
            detailedScheduleDTO.setContents(contents);
            detailedScheduleList.add(modelMapper.map(detailedScheduleDTO, DetailedSchedule.class));
        }
        detailedScheduleRepository.saveAll(detailedScheduleList);
    }
}
