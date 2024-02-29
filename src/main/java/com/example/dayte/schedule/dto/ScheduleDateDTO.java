package com.example.dayte.schedule.dto;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.schedule.domain.ScheduleDateId;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ScheduleDateDTO {

    // mapper.map 으로 매핑 후 저장할 값
    private ScheduleDateId scheduleDateId;

    private AdminContents contents;

    // JS 에서 넘어오는 값
    private List<String> contentsList;

    private String uuid;

    private LocalDate nowDate;
}
