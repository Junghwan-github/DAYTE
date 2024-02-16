package com.example.dayte.inquiry.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailQuestionDTO {

    private String title;

    private String content;

    private String emailAdress;

    private String mainCategory;

    private String subCategory;



}
