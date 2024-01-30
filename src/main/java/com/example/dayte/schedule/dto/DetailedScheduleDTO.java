package com.example.dayte.schedule.dto;

import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.domain.ScheduleDate;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailedScheduleDTO {

    private Contents contents;

    private ScheduleDate scheduleDate;
}
