package com.example.dayte.admin.contents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminContentsImage {

    // 상세보기에서 보여질 이미지들
    @Id
    @Column(nullable = false, length = 500)
    private String imageURL;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "adminContentsId")
    private AdminContents adminContents;
}
