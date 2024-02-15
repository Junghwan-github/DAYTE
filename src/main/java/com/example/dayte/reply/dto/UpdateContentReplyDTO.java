package com.example.dayte.reply.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContentReplyDTO {

    private String uuid;

    private String newReply;

    private int rating;

    private Timestamp createDate;

}
