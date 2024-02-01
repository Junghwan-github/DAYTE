package com.example.dayte.admin.contents.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminContentsImage {

    // 상세보기에서 보여질 이미지들
    @Id
    @Column(nullable = false, length = 500)
    private String imageURL;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "adminContentsId")
    private AdminContents adminContents;

}
