package com.example.dayte.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexMainSliderDTO {

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String images;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String category;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String mainTitle;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String subTitle;

    private String schedule;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String address;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String summary;

}
