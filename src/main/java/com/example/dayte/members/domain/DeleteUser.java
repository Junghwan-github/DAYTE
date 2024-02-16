package com.example.dayte.members.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DeleteUser { // 회원탈퇴 회원 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userEmail;

    private LocalDate deleteDate;

}
