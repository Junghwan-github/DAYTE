package com.example.dayte.reply.dto;

import com.example.dayte.members.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class ContentReplyDTO {

    private User user;

    private String content;

    private int rating;

    private Timestamp createDate;

    private String FormatDate;

    private String uuid;

}
