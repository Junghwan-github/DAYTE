package com.example.dayte.admin.mianslider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    // HTTP 응답 상태 코드
    private int status;

    // 응답 데이터
    private T data;
}