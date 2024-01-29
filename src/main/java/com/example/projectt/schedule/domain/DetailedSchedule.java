package com.example.projectt.schedule.domain;

import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.domain.ScheduleDate;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne
    @JoinColumn(name = "contentId")
    private Contents contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(
                    name = "uuid", referencedColumnName = "uuid"),
            @JoinColumn(
                    name = "nowDate", referencedColumnName = "now_date")
    })
    private ScheduleDate scheduleDate;
}
