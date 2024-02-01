package com.example.dayte.admin.contents.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminContents {

    // 일정추가 시 보일 리스트에서 보여질 부분

    @Id
    private String uuid;

    private String businessName;

    private String category;

    private String gu;

    @Column(nullable = false, precision = 9, scale = 7)
    private BigDecimal positionX;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal positionY;

    private String detailedAddress;

    private String keyword;

    @Column(columnDefinition = "TEXT")
    private String detailedDescription; // 상세설명

    @OneToMany(
            mappedBy = "adminContents",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    private List<AdminContentsImage> adminContentsImageList;

}
