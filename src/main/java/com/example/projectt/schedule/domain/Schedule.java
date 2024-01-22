package com.example.projectt.schedule.domain;

import com.example.projectt.members.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "userEmail")
    private User user;

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
