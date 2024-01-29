package com.example.dayte.schedule.dto;

import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.domain.ScheduleDateId;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDateDTO {

    // mapper.map 으로 매핑 후 저장할 값
    private ScheduleDateId scheduleDateId;

    private Contents contents;

    // JS 에서 넘어오는 값
    private List<String> contentsList;

    private String uuid;

    private LocalDate nowDate;
}
