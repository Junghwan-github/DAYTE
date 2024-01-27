package com.example.dayte.schedule.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class ScheduleDTO {

    private List<?> contents;

    private String uuid;

    private int nextDays;

}
