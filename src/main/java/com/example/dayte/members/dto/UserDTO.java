package com.example.dayte.members.dto;

import com.example.dayte.members.domain.RoleType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "이메일 아이디를 작성하세요.")
    @NotBlank(message = "이메일 아이디는 필수 입력 항목입니다.")
    @Email(message = "이메일 양식을 지켜주세요.")
    private String userEmail; //

    @NotNull(message = "비밀번호를 작성하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*?_]).{8,20}$", message = "비밀번호는 숫자, 영어 대소문자, 특수기호를 각각 포함해야 합니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해 주세요")
    private String password;

    @NotNull(message = "이름을 작성하세요.")
    @NotBlank(message = "이름 필수 입력 항목입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "이름은 한글, 영어 대소문자로만 입력해 주세요.")
    private String userName;

    @NotNull(message = "닉네임을 작성하세요.")
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해 주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 한글, 숫자, 영어 대소문자로만 입력해 주세요.")
    private String nickName;

    @NotNull(message = "휴대전화번호를 작성하세요.")
    @NotBlank(message = "휴대전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(01[016789])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대전화번호는 10~11자의 숫자만 사용하여야 합니다.")
    private String phone;

    @NotNull(message = "생년월일을 작성하세요.")
    @NotBlank(message = "생년월일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$", message = "생년월일은 8자의 숫자만 사용해 주세요.")
    private String birthDate; //

    private String gender; //

    private boolean del;

    private RoleType role;

    private boolean social;

    private Timestamp joinDate;

    private MultipartFile image;

    private String profileImageName; // 프로필 이미지 이름
    
    private String profileImagePath; // 프로필 이미지 경로

    private LocalDate loginDate;

    private boolean notification;

    private String black;

    private Timestamp blockDate;

}
