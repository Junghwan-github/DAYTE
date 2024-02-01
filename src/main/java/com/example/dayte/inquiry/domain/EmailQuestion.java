package com.example.dayte.inquiry.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmailQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // db에 varchar를 넘어서 더 큰 값을 넣기 위한 작업
    @Column(nullable = false)
    private String content;

    @Column
    private boolean answerCheck;


}
