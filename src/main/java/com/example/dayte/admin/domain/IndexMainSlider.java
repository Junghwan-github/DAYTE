package com.example.dayte.admin.domain;

import com.example.dayte.members.domain.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "INDEXMAINSLIDER")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndexMainSlider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article;

    @Column(nullable = false, length = 200)
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

    @Column(nullable = false, length = 200)
    private String summary;

}
