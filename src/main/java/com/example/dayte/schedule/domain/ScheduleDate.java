package com.example.dayte.schedule.domain;

import com.example.dayte.members.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "schedule", "nowDate" }) })
@Entity
public class ScheduleDate {

    @EmbeddedId
    private ScheduleDateId scheduleDateId;

    @OneToMany(mappedBy = "scheduleDate",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<DetailedSchedule> detailedScheduleList;

}
