package com.example.projectt.schedule.service;

import com.example.projectt.members.domain.User;
import com.example.projectt.schedule.domain.ScheduleDate;
import com.example.projectt.schedule.dto.ScheduleDTO;
import com.example.projectt.schedule.dto.ScheduleDateDTO;
import com.example.projectt.schedule.persistence.ScheduleDateRepository;
import com.example.projectt.security.dto.UserSecurityDTO;
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
            User user = mapUserDTOToUser(userSecurityDTO);
            LocalDate startDate = parseDateString(scheduleDTO.getStartDate());
            LocalDate endDate = parseDateString(scheduleDTO.getEndDate());
            String title = scheduleDTO.getTitle();

            String uuid = generateUUID();
            ScheduleDate scheduledate = ScheduleDate.builder()
                    .uuid(uuid)
                    .title(title)
                    .user(user)
                    .startDate(startDate)
                    .endDate(endDate)
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
            List<ScheduleDate> result = scheduleDateRepository.findByUserOrderByStartDate(user);
            return result;
        } catch (Exception e) {
            handleException("사용자 일정 확인에 실패했습니다", e);
            return null;
        }
    }

    @Transactional(readOnly = true)
    public ScheduleDate getUserScheduleDate(ScheduleDateDTO scheduleDateDTO, UserSecurityDTO userSecurityDTO) {
            User user = mapUserDTOToUser(userSecurityDTO);
            LocalDate startDate = parseDateString(scheduleDateDTO.getStartDate());
            ScheduleDate result = scheduleDateRepository.findByUserAndStartDate(user, startDate).orElseGet(() ->{
                return new ScheduleDate();
            });
            return result;
    }

    public void deleteSchedule(LocalDate startDate, UserSecurityDTO userSecurityDTO) {
        User user = mapUserDTOToUser(userSecurityDTO);
        ScheduleDate scheduleDate = scheduleDateRepository.findByUserAndStartDate(user,startDate).get();
        scheduleDateRepository.deleteById(scheduleDate.getUuid());
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private User mapUserDTOToUser(UserSecurityDTO userSecurityDTO) {
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
