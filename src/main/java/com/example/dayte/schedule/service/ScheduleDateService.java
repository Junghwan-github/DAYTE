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

    public void updateSchedule(ScheduleDateDTO scheduleDateDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleDateDTO.getUuid()).get();

        // DB 에 있는 Contents
        List<DetailedSchedule> dbScheduleList = new ArrayList<>();

        // 일정 수정으로 변경한 Contents
        List<DetailedSchedule> addSchedulesList = new ArrayList<>();
        List<Contents> addContentsList = contentsRepository.findAllById(scheduleDateDTO.getContentsList());
        for (ScheduleDate scheduleDate : schedule.getScheduleDates()) {
            if (scheduleDate.getScheduleDateId().getNowDate().equals(scheduleDateDTO.getNowDate())) {
                dbScheduleList = scheduleDate.getDetailedScheduleList();
            }
        }

        for (Contents contents : addContentsList) {
            DetailedScheduleDTO scheduleDTO = new DetailedScheduleDTO();
            scheduleDTO.setContents(contents);
            scheduleDTO.setScheduleDate(dbScheduleList.get(0).getScheduleDate());
            addSchedulesList.add(modelMapper.map(scheduleDTO, DetailedSchedule.class));
        }

        List<DetailedSchedule> toAddContents = new ArrayList<>(addSchedulesList);
        toAddContents.removeAll(dbScheduleList);
        toAddContents.forEach(content -> {
            System.out.println("content.getId()1: " + content.getContents());
        });


        List<DetailedSchedule> toRemoveContents = new ArrayList<>(dbScheduleList);
        toRemoveContents.removeAll(addSchedulesList);
        toRemoveContents.forEach(content -> {
            System.out.println("content.getId()2: " + content.getContents());
        });

        // List 타입 바꾸기
        detailedScheduleRepository.saveAll(toAddContents);
        toRemoveContents.forEach(detailedSchedule -> {
            System.out.println("content.getId()3: " + detailedSchedule.getContents());
            detailedScheduleRepository.deleteById(detailedSchedule.getId());
        });
    }
}
