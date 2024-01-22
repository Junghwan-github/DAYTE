package com.example.projectt.schedule.domain;

import com.example.projectt.members.domain.User;
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

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "userEmail")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

}
