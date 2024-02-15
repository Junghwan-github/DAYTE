package com.example.dayte.admin.mianslider.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private LocalDate date;

    private int visitors;

}
