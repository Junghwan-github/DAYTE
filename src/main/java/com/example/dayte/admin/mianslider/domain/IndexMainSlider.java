package com.example.dayte.admin.mianslider.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ADMIN_INDEX")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndexMainSlider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article;

    @Column(nullable = false, length = 500)
    private String images;

    @Column(nullable = false, length = 200)
    private String category;

    @Column(nullable = false, length = 200)
    private String mainTitle;

    @Column(nullable = false, length = 200)
    private String subTitle;

    @Column(length = 200)
    private String schedule;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 200)
    private String href;

}
