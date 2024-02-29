package com.example.dayte.post.domin;

import com.example.dayte.members.domain.User;
import com.example.dayte.reply.domain.PostReply;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String title;

    @Setter
    @Lob // db에 varchar를 넘어서 더 큰 값을 넣기 위한 작업
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "Timestamp")
    private Timestamp createDate;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)// N : 1
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("num desc")
    private List<PostReply> replyList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<PostImages> postImages;

}
