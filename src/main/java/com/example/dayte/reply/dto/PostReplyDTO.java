package com.example.dayte.reply.dto;

import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class PostReplyDTO {

    private int num;

    private User user;

    private Post post;

    private String content;

    private int id;

    private Timestamp createDate;

    private String formatDate;

}
