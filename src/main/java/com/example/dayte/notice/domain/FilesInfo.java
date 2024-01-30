package com.example.dayte.notice.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FilesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private int id;

    @Column
    @Setter
    @Getter
    private String originalName;

    @Column
    @Setter
    @Getter
    private String saveName;

    @Column
    private long fileSize;

    @Column
    private boolean deleteCheck;

    @CreationTimestamp
    private Timestamp createDate;

    @Column
    private LocalDateTime deletedDate;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "no")
    private Notice notice;












}
