package com.example.projectt.schedule.dto;


import com.example.projectt.members.domain.User;
import com.example.projectt.schedule.domain.Contents;
import com.example.projectt.schedule.domain.ScheduleDate;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class ScheduleDTO {

    private List<?> contents;

    private String uuid;

    private int nextDays;

}
