package com.example.dayte.schedule.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDTO {
    private String startDate;

    private String endDate;

    private String userEmail;

    private String title;

    private String uuid;
}
