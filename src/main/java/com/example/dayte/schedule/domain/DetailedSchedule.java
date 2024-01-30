package com.example.dayte.schedule.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Table
@Entity
public class DetailedSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contentId", unique = false)
    private Contents contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(
                    name = "uuid", referencedColumnName = "uuid", nullable = false),
            @JoinColumn(
                    name = "nowDate", referencedColumnName = "now_date", nullable = false)
    })
    private ScheduleDate scheduleDate; // schedule,nowDate
}
