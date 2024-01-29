package com.example.projectt.schedule.dto;

import com.example.projectt.schedule.domain.Contents;
import com.example.projectt.schedule.domain.ScheduleDate;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailedScheduleDTO {

    private Contents contents;

    private ScheduleDate scheduleDate;
}
