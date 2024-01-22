package com.example.projectt.members.dto;

import com.example.projectt.members.domain.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "EmailId를 작성하세요.")
    @NotBlank(message = "EmailId는 필수 입력 항목입니다.")
    private String userEmail;

    @NotNull(message = "비밀번호를 작성하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 20, message = "8자 ~ 20자 이하로 입력해주세요")
    private String password;

    @NotNull(message = "이름을 작성하세요.")
    @NotBlank(message = "이름 필수 입력 항목입니다.")
    private String userName;

    @NotNull(message = "닉네임을 작성하세요.")
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    private String nickName;

    @NotNull(message = "휴대전화번호를 작성하세요.")
    @NotBlank(message = "휴대전화번호는 필수 입력 항목입니다.")
    private String phone;

    @NotNull(message = "생년월일을 작성하세요.")
    @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    private String birthDate;

    private String gender;

    private RoleType role;
}
