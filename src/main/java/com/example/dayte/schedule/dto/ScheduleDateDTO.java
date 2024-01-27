package com.example.dayte.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDateDTO {
    private String startDate;
    private String endDate;
    private String userEmail;
    private String title;
    private String uuid;
}
