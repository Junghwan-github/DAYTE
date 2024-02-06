package com.example.dayte.myReview.domain;

import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
