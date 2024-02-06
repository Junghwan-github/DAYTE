package com.example.dayte.reply.domain;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.members.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @JoinColumn(name = "user")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; // 사용자 이름


    @JoinColumn (name = "contents")
    @ManyToOne
    private AdminContents contents;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @Setter
    @Column(name = "star")
    private Integer rating;

    private boolean deleted; // 댓글의 삭제 여부

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createDate; // 댓글 등록일



}
