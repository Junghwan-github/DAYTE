package com.example.dayte.members.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SocialResponseDTO<T> {

    // HTTP 응답 상태 코드
    private int status;

    // 응답 데이터
    private T data;

    private String socialName;

    private OAuth2AccessToken accessToken;

    private ClientRegistration client;
}
