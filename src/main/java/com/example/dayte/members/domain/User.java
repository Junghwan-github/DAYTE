package com.example.dayte.members.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneColumn;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Setter
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

    @CreationTimestamp
    private Timestamp joinDate;

    @Setter
    private String profileImageName;

    @Setter
    private String profileImagePath;

    @Setter
    private LocalDate loginDate;

    @Setter
    private boolean notification; // 휴면계정 전환 예정 이메일을 보냈는지 파악하는 컬럼

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "Timestamp")
    private Timestamp blockDate;

    // 비밀번호 변경 메서드
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

