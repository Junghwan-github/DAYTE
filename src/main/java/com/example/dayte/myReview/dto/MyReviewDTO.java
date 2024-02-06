package com.example.dayte.myReview.dto;

import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyReviewDTO {

    private long id;

    private User user;

    private Post post;

}
