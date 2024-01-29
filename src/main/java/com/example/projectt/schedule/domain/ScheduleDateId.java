package com.example.projectt.schedule.domain;

import com.example.dayte.schedule.domain.Schedule;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class ScheduleDateId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid")
    private Schedule schedule;

    @Temporal(TemporalType.DATE)
    @Column(name = "now_date")
    private LocalDate nowDate;


}