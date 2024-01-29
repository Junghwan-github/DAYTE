package com.example.dayte.schedule.domain;

import com.example.dayte.members.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Embeddable
public class Schedule {

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

    @OneToMany(mappedBy = "scheduleDateId.schedule",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private List<ScheduleDate> scheduleDates;

}

