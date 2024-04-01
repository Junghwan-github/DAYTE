package com.example.dayte.schedule.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
