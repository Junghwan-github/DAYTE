package com.example.projectt.schedule.domain;

import com.example.projectt.members.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contentId", referencedColumnName = "id")
    private Contents contents;

    @ManyToOne
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private ScheduleDate scheduleDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "now_date")
    private LocalDate nowDate;

    @Column(nullable = false)
    private int sequence;
}
