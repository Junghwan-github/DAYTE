package com.example.dayte.schedule.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@ToString
public class Contents {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String gu;

    @Column(nullable = false)
    private String ro;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal positionX;

    @Column(nullable = false, precision = 9, scale = 7)
    private BigDecimal positionY;

}