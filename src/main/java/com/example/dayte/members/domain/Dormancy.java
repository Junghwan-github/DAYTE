package com.example.dayte.members.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dormancy {

    @Id
    @JoinColumn(name = "user_email")
    private String userEmail; // 로그인 이메일 아이디

    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 50, unique = true)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false)
    private String birthDate;

    @Column
    private String gender;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private boolean del;

    private boolean social;

    private Timestamp joinDate;

    private String profileImageName;

    private String profileImagePath;

    private LocalDate dormancyDate;

}
