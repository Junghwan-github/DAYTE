package com.example.dayte.schedule.service;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.domain.ScheduleDateId;
import com.example.dayte.schedule.dto.ScheduleDTO;
import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.domain.Schedule;
import com.example.dayte.schedule.dto.ScheduleDateDTO;
import com.example.dayte.schedule.persistence.ContentsRepository;
import com.example.dayte.schedule.persistence.ScheduleDateRepository;
import com.example.dayte.schedule.persistence.ScheduleRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleDateRepository scheduleDateRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 일정 추가
    @Transactional
    public void insertSchedule(UserSecurityDTO userSecurityDTO, ScheduleDTO scheduleDTO) {
        try {
            String uuid = generateUUID();
            String title = scheduleDTO.getTitle();
            User user = modelMapper.map(userSecurityDTO, User.class);
            LocalDate startDate = parseDateString(scheduleDTO.getStartDate());
            LocalDate endDate = parseDateString(scheduleDTO.getEndDate());

            Schedule schedule = Schedule.builder()
                    .uuid(uuid)
                    .title(title)
                    .user(user)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            scheduleRepository.save(schedule);

            for (int i = 0; i < endDate.toEpochDay() - startDate.toEpochDay() + 1; i++) {
                ScheduleDateDTO scheduleDateDTO = new ScheduleDateDTO();
                ScheduleDateId scheduleDateId = new ScheduleDateId();
                scheduleDateId.setSchedule(schedule);
                scheduleDateId.setNowDate(startDate.plusDays(i));
                scheduleDateDTO.setScheduleDateId(scheduleDateId);
                scheduleDateRepository.save(modelMapper.map(scheduleDateDTO, ScheduleDate.class));
            }

        } catch (Exception e) {
            handleException("일정 추가에 실패했습니다", e);
        }
    }

    // 사용자가 세운 일정 목록 전체를 반환하는 메서드
    @Transactional
    public List<Schedule> selectScheduleByUser(UserSecurityDTO userSecurityDTO) {
        try {
            User user = modelMapper.map(userSecurityDTO, User.class);
            List<Schedule> result = scheduleRepository.findAllByUserOrderByStartDate(user);
            return result;
        } catch (Exception e) {
            handleException("사용자 일정 확인에 실패했습니다", e);
            return null;
        }
    }

    // 사용자의 일정 유무를 비교하는 메서드
    @Transactional(readOnly = true)
    public Schedule getUserSchedule(ScheduleDTO scheduleDTO, UserSecurityDTO userSecurityDTO) {
        User user = modelMapper.map(userSecurityDTO, User.class);
        LocalDate startDate = parseDateString(scheduleDTO.getStartDate());

        Schedule result = scheduleRepository.findByUserAndStartDate(user, startDate).orElseGet(() -> {
            return new Schedule();
        });
        return result;
    }

    // 사용자가 선택한 일정 전체를 삭제하는 메서드
    @Transactional
    public void deleteSchedule(LocalDate startDate, UserSecurityDTO userSecurityDTO) {
        User user = modelMapper.map(userSecurityDTO, User.class);
        Schedule scheduleDate = scheduleRepository.findByUserAndStartDate(user,startDate).get();
        scheduleRepository.deleteById(scheduleDate.getUuid());
    }

    // 랜덤한 UUID 생성하는 메서드
    private String generateUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (scheduleRepository.findById(uuid).isPresent());

        return uuid;
    }

    // String 타입으로 들어온 매개변수를 LocalDate 타입으로 변환하여 반환하는 메서드
    private LocalDate parseDateString(String dateString) {
        String pattern = "yyyyMMdd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString, formatter);
    }

    // 예외처리 메서드
    private void handleException(String message, Exception e) {
        e.printStackTrace();
        throw new RuntimeException(message);
    }
}

