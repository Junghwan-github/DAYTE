package com.example.projectt.schedule.service;


import com.example.projectt.members.User;
import com.example.projectt.members.dto.UserSecurityDTO;
import com.example.projectt.schedule.domain.ScheduleDate;
import com.example.projectt.schedule.dto.ScheduleDateDTO;
import com.example.projectt.schedule.persistence.ScheduleDateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleDateService {

    @Autowired
    private ScheduleDateRepository scheduleDateRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 일정 추가
    @Transactional
    public void insertScheduleDate(UserSecurityDTO userSecurityDTO, ScheduleDateDTO scheduleDTO) {
        try {
            // UUID 생성 및 필요한 정보로 ScheduleDate 생성 후 저장
            String uuid = generateUUID();
            User user = mapUserDTOToUser(userSecurityDTO);

            LocalDate startDay = parseDateString(scheduleDTO.getStartDate());
            LocalDate endDay = parseDateString(scheduleDTO.getEndDate());

            ScheduleDate scheduledate = ScheduleDate.builder()
                    .uuid(uuid)
                    .user(user)
                    .startDate(startDay)
                    .endDate(endDay)
                    .build();
            scheduleDateRepository.save(scheduledate);
        } catch (Exception e) {
            handleException("일정 추가에 실패했습니다", e);
        }
    }

    @Transactional
    public ScheduleDate findScheduleByUserAndStartDate(UserSecurityDTO userSecurityDTO, ScheduleDateDTO scheduleDTO) {
        try {
            // 사용자와 시작 날짜를 기반으로 일정 조회
            User user = mapUserDTOToUser(userSecurityDTO);
            LocalDate startDay = parseDateString(scheduleDTO.getStartDate());

            Optional<ScheduleDate> result = scheduleDateRepository.findByUserAndStartDate(user, startDay);
            return result.orElse(null);
        } catch (Exception e) {
            handleException("사용자 및 시작 날짜별 일정 확인에 실패했습니다", e);
            return null;
        }
    }

    @Transactional
    public List<ScheduleDate> selectScheduleByUser(UserSecurityDTO userSecurityDTO) {
        try {
            User user = modelMapper.map(userSecurityDTO, User.class);
            List<ScheduleDate> result = scheduleDateRepository.findByUser(user);
            return result;
        } catch (Exception e) {
            handleException("사용자 일정 확인에 실패했습니다", e);
            return null;
        }
    }

    @Transactional
    public ScheduleDate selectScheduleDate(String uuid) {
        try {
            // UUID를 기반으로 일정 조회
            Optional<ScheduleDate> result = scheduleDateRepository.findById(uuid);
            return result.orElseGet(null);
        } catch (Exception e) {
            handleException("UUID별 일정 확인에 실패했습니다", e);
            return null;
        }
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public User mapUserDTOToUser(UserSecurityDTO userSecurityDTO) {
        return modelMapper.map(userSecurityDTO, User.class);
    }

    private LocalDate parseDateString(String dateString) {
        String pattern = "yyyyMMdd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString, formatter);
    }

    private void handleException(String message, Exception e) {
        e.printStackTrace();
        throw new RuntimeException(message);
    }
}
