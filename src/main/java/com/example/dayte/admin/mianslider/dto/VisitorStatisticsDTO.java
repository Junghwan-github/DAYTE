package com.example.dayte.admin.mianslider.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisitorStatisticsDTO {

    private int year;

    private int month;

    private int averageVisitors;

    private long id;

    private LocalDate date;

    private int visitors;

    private String dayOfWeek;

    public VisitorStatisticsDTO(int year, int month, int averageVisitors) {
        this.year = year;
        this.month = month;
        this.averageVisitors = averageVisitors;
    }
    public VisitorStatisticsDTO(long id, LocalDate date, int visitors) {
        this.id = id;
        this.date = date;
        this.visitors = visitors;
        this.dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
    };
}
