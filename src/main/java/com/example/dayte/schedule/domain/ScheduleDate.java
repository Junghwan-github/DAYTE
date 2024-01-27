package com.example.dayte.schedule.domain;

import com.example.dayte.members.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScheduleDate {

    @Id
    @Column(name = "uuid", nullable = false, length = 100)
    private String uuid;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "userEmail", nullable = false)
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

}
