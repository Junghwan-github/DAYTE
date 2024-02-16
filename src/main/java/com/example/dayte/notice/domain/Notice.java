package com.example.dayte.notice.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp lastModDate;

    @Column
    private int priority;

    @Column
    @Builder.Default
    private boolean viewCheck = true;

    @OneToMany(mappedBy = "notice", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<FilesInfo> files;





}
