package com.example.projectt.schedule.dto;

import com.example.projectt.members.domain.User;
import com.example.projectt.schedule.domain.Contents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private User user;

    private Contents contents;

    private LocalDate nowDate;

    private int sequence;

}
