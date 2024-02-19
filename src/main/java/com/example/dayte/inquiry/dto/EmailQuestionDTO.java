package com.example.dayte.inquiry.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    private List<MultipartFile> files;





}
