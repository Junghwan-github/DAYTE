package com.example.dayte.schedule.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckScheduleDTO<T> {

    private String uuid;

    private boolean overlap;

    // HTTP 응답 상태 코드
    private int status;

    // 응답 데이터
    private T data;

    public CheckScheduleDTO(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean getOverlap() {
        return this.overlap;
    }

}
