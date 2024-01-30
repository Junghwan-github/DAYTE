package com.example.dayte.security.service;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 소셜 로그인 서비스
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 클라이언트 이름 가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName) {
            case "kakao" -> email = getKakaoEmail(paramMap);
            case "Google" -> email = getGoogleEmail(paramMap);
            case "Naver" -> email = getNaverEmail(paramMap);
        }

        return generatedDTO(email, paramMap);
    }

    private UserSecurityDTO generatedDTO(String email, Map<String, Object> paramMap) {
        Optional<User> result = userRepository.findById(email);

        char[] arr = {'@', '$', '!', '%', '*', '#', '?', '&'};

        String randomStr = "";

        for (int i = 0; i < 3; i++) {
            randomStr += RandomStringUtils.random(6,true,true);
            if (i != 2)
                randomStr += arr[new Random().nextInt(7)];
        }

        User user;
        if (result.isEmpty()) { // API 로그인이 처음인 사람일 경우

            user = User.builder()
                    .userEmail(email)
                    .password(passwordEncoder.encode(randomStr))
                    .nickName(email) // 임의의 값 넣기
                    .userName(email.substring(0, email.lastIndexOf("@")))
                    .phone("010-0000-0000") // 임의의 값 넣기
                    .birthDate("99999999") // 임의의 값 넣기
                    .del(false)
                    .role(RoleType.USER)
                    .build();

            userRepository.save(user);

            UserSecurityDTO userSecurityDTO = new UserSecurityDTO(
                    email, // userEmail
                    passwordEncoder.encode(randomStr), // password
                    email.substring(0, email.lastIndexOf("@")), // userName
                    email, // nickName
                    "010-0000-0000", // phone
                    "99999999", // birthDate
                    "", // gender
                    false, // del
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")) // RoleType
            );

            userSecurityDTO.setProps(paramMap); // paramMap : 엑세스 토큰 발급받으면서 넘어온 객체

            return userSecurityDTO;
        }

        user = result.get();

        return new UserSecurityDTO(
                user.getUserEmail(),
                user.getPassword(),
                user.getNickName(),
                user.getUserName(),
                user.getPhone(),
                user.getBirthDate(),
                user.getGender(),
                user.isDel(),
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
                ));

    }

    private String getGoogleEmail(Map<String, Object> paramMap) {
        return (String) paramMap.get("email");
    }

    private String getNaverEmail(Map<String, Object> paramMap) {
        Map<String, String> response = (Map<String, String>) paramMap.get("response");
        return response.get("email");
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {
        var accountMap = (LinkedHashMap) paramMap.get("kakao_account");
        return (String) accountMap.get("email");
    }

}
