package com.example.dayte.schedule.dto;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.schedule.domain.ScheduleDate;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailedScheduleDTO {

    private AdminContents adminContents;

    private ScheduleDate scheduleDate;
}
