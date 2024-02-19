package com.example.dayte.admin.contents.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PageableDTO {

    private int contentsStartPage;

    private int contentsEndPage;

    private int postNowPage;

    private String field;

    private String word;

}
