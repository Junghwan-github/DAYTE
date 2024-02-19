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

    private int newRating;

    private Timestamp createDate;

}
