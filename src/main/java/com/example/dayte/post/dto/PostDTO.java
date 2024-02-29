package com.example.dayte.post.dto;


import com.example.dayte.members.domain.User;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    @NotNull(message = "title의 내용을 작성하세요.")
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @NotNull(message = "Content의 내용을 작성하세요.")
    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    private User user;
}