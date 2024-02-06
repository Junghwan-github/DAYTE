package com.example.dayte.post.domin;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String imageUrl;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}