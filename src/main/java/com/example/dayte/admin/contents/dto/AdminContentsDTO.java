package com.example.dayte.admin.contents.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class AdminContentsDTO implements Serializable {

    private String uuid;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String businessName;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String category;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String gu;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "위도값을 숫자로 입력해주세요.")
    private BigDecimal positionX;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "경도값을 숫자로 입력해주세요.")
    private BigDecimal positionY;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String detailedAddress;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String keyword;

    @NotNull(message = "필수 등록사항입니다.")
    @NotBlank(message = "필수 등록사항입니다.")
    private String detailedDescription;

    private List<MultipartFile> imageFiles;

}
