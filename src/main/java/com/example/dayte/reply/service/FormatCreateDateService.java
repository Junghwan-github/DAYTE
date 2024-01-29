package com.example.dayte.reply.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FormatCreateDateService {

    // 수정한 시간 반환하는 메서드
    public String getFormattedCreateDate(Timestamp createDate) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime commentTime = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // toInstant() : createDate 에 담긴 시간을 Instant 타입으로 변환(.toString()과 비슷한 역할)
        // atZone(ZoneId.systemDefault()) : 매개변수에 담긴 시간으로 변환
        // ZoneId.systemDefault() : JAVA 프로그램에 설정되어 있는 시간의 기준이 되는 지역(한국이면 "Asia/Seoul" / 미국이면 "America/New_York" )
        // toLocalDateTime() : LocalDateTime 타입으로 변환(.toString()과 비슷한 역할)

        Duration duration = Duration.between(commentTime, currentTime);
        // Duration.between(commentTime, currentTime) : 매개변수로 넘어간 두 시간의 간격을 계산하여 반환

        if (duration.toMinutes() < 1) { // toMinutes() : 계산한 시간 중 분 단위를 반환
            return "방금 전";
        } else if (duration.toHours() < 1) { // toHours() : 계산한 시간 중 시 단위를 반환
            return duration.toMinutes() + "분 전";
        } else if (duration.toDays() < 1) { // toDays() : 계산한 시간 중 일 단위를 반환
            return duration.toHours() + "시간 전";
        } else if (duration.toDays() < 30) {
            return duration.toDays() + "일 전";
        } else {
            // SimpleDateFormat dateFormat = ;
            // SimpleDateFormat 날짜와 시간을 내가 원하는 형태로 변환
            return new SimpleDateFormat("yyyy-MM-dd").format(createDate);
            // dateFormat.format(createDate) : 매개변수를 String 타입으로 변환
        }
    }
}
