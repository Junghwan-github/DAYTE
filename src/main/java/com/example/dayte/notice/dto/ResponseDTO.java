package com.example.dayte.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // setter, getter 둘 다 필요
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> { //응답 전용으로 사용, 어떤 형식으로도 받도록 제네릭 타입으로 지정
    // 반환할 것 -> http 응답상태코드(404, 500 등)
    private int status;

    //실제로 응답할 데이터
    private T data;

}
