package com.example.dayte.schedule.service;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.DetailedSchedule;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.domain.ScheduleDateId;
import com.example.dayte.schedule.dto.DetailedScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDTO;
import com.example.dayte.schedule.dto.ScheduleDateDTO;
import com.example.dayte.schedule.persistence.ContentsRepository;
import com.example.dayte.schedule.persistence.DetailedScheduleRepository;
import com.example.dayte.schedule.persistence.ScheduleDateRepository;
import com.example.dayte.schedule.persistence.ScheduleRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleDateService {

    private final ScheduleDateRepository scheduleDateRepository;

    private final ScheduleRepository scheduleRepository;

    private final AdminContentsRepository adminContentsRepository;

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
        for (AdminContents contents : adminContentsRepository.findAllById(scheduleDateDTO.getContentsList())) {
            detailedScheduleDTO.setAdminContents(contents);
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
        List<AdminContents> addContentsList = adminContentsRepository.findAllById(scheduleDateDTO.getContentsList());
        for (ScheduleDate scheduleDate : schedule.getScheduleDates()) {
            if (scheduleDate.getScheduleDateId().getNowDate().equals(scheduleDateDTO.getNowDate())) {
                dbScheduleList = scheduleDate.getDetailedScheduleList();
            }
        }

        for (AdminContents contents : addContentsList) {
            DetailedScheduleDTO scheduleDTO = new DetailedScheduleDTO();
            scheduleDTO.setAdminContents(contents);
            scheduleDTO.setScheduleDate(dbScheduleList.get(0).getScheduleDate());
            addSchedulesList.add(modelMapper.map(scheduleDTO, DetailedSchedule.class));
        }

        List<DetailedSchedule> toAddContents = new ArrayList<>(addSchedulesList);
        toAddContents.removeAll(dbScheduleList);
        toAddContents.forEach(content -> {
        });


        List<DetailedSchedule> toRemoveContents = new ArrayList<>(dbScheduleList);
        toRemoveContents.removeAll(addSchedulesList);
        toRemoveContents.forEach(content -> {
        });

        // List 타입 바꾸기
        detailedScheduleRepository.saveAll(toAddContents);
        toRemoveContents.forEach(detailedSchedule -> {
            detailedScheduleRepository.deleteById(detailedSchedule.getId());
        });
    }
}
