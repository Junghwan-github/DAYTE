package com.example.dayte.reply.domain;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ContentReply")
public class ContentReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num; // 댓글 번호

    @JoinColumn(name = "userEmail")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; // 사용자 이름

    @JsonIgnore
    @JoinColumn (name = "contents")
    @ManyToOne
    private AdminContents contents;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @Setter
    @Column(name = "star")
    private Integer rating;

    private boolean deleted; // 댓글의 삭제 여부

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createDate; // 댓글 등록일

}
