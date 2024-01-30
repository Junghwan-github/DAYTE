package com.example.dayte.notice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class NoticeDTO {

    @NotNull(message = "title의 내용을 작성하세요.")
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;


    @NotNull(message = "Content의 내용을 작성하세요.")
    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;





}
