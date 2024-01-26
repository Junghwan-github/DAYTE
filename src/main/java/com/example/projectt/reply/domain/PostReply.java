package com.example.projectt.reply.domain;

import com.example.projectt.members.domain.User;
import com.example.projectt.post.domin.Post;
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
@Table(name = "postReply")
public class PostReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num; // 댓글 번호

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user; // 사용자 이름

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Post post; // 댓글과 연관된 포스트

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    private boolean deleted; // 댓글의 삭제 여부

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createDate; // 댓글 등록일

}