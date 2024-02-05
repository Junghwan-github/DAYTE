package com.example.dayte.members.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @Column(nullable = false, length = 100)
    private String userEmail; // 로그인 이메일 아이디

    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @Column(nullable = false, length = 50)
    private String userName;

    @Setter
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

    @CreationTimestamp
    private Timestamp joinDate;

    @Setter
    private String profileImageName;

    @Setter
    private String profileImagePath;

    @Setter
    private LocalDate loginDate;
}

