package com.example.dayte.security.service;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.DeleteUserRepository;
import com.example.dayte.members.persistence.DormancyRepository;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import com.example.dayte.security.handler.LoginFailHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final DormancyRepository dormancyRepository;
    private final DeleteUserRepository deleteUserRepository;
    private final ModelMapper modelMapper;
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

        return generatedDTO(email, paramMap, clientName);
    }

    private UserSecurityDTO generatedDTO(String email, Map<String, Object> paramMap, String clientName) {
        Optional<User> result = userRepository.findById(email);

        char[] arr = {'@', '$', '!', '%', '*', '#', '?', '&'};

        String randomStr = "";

        for (int i = 0; i < 3; i++) {
            randomStr += RandomStringUtils.random(6,true,true);
            if (i != 2)
                randomStr += arr[new Random().nextInt(7)];
        }

        User user;
        UserSecurityDTO userSecurityDTO;
        if (result.isEmpty()) { // API 로그인이 처음인 사람일 경우

            user = User.builder()
                    .userEmail(email)
                    .password(passwordEncoder.encode(randomStr))
                    .nickName(email) // 임의의 값 넣기
                    .userName(email.substring(0, email.lastIndexOf("@")))
                    .phone("010-0000-0000") // 임의의 값 넣기
                    .birthDate("99999999") // 임의의 값 넣기
                    .del(false)
                    .social(true)
                    .role(RoleType.USER)
                    .profileImagePath("/images/default_icon_profile.png")
                    .profileImageName("default_icon_profile.png")
                    .build();
            user.setLoginDate(LocalDate.now());

            userRepository.save(user);

            userSecurityDTO = new UserSecurityDTO(
                    email, // userEmail
                    passwordEncoder.encode(randomStr), // password
                    email.substring(0, email.lastIndexOf("@")), // userName
                    email, // nickName
                    "010-0000-0000", // phone
                    "99999999", // birthDate
                    "", // gender
                    false, // del
                    "/images/default_icon_profile.png", // profileImagePath
                    true,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")) // RoleType
            );

            userSecurityDTO.setProps(paramMap); // paramMap : 엑세스 토큰 발급받으면서 넘어온 객체
            userSecurityDTO.setSocialName(clientName);
            return userSecurityDTO;
        }

        // 1회 이상 로그인 경험이 있는 경우
        user = result.get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (user.getRole() == RoleType.DORMANCY)
            throw new DisabledException("귀하의 계정은 " +
                    dormancyRepository.findById(user.getUserEmail()).get().getDormancyDate() +
                    " 부로 휴면 상태로 전환된 계정입니다."); // 계정 비활성화
        else if (user.getRole() == RoleType.BLOCK) {
            if(user.getBlockDate().toLocalDateTime().isAfter(LocalDateTime.now())) {
                System.out.println("====================== 통과 ======================");
                throw new LockedException("귀하의 계정은 사용 정지되어 " +
                        user.getBlockDate().toLocalDateTime().getYear() + "년 " +
                        user.getBlockDate().toLocalDateTime().getMonthValue() + "월 " +
                        user.getBlockDate().toLocalDateTime().getDayOfMonth() + "일 " +
                        user.getBlockDate().toLocalDateTime().getHour() + "시 " +
                        user.getBlockDate().toLocalDateTime().getMinute() + "분" +
                        " 이후부터 사용 가능한 계정입니다."); // 계정 잠김
            } else {
                userDTO.setRole(RoleType.USER);
                userRepository.save(modelMapper.map(userDTO, User.class));
            }
        }
        else if (user.isDel())
            throw new AccountExpiredException("귀하의 계정은 " +
                    deleteUserRepository.findByUserEmail(user.getUserEmail()).get().getDeleteDate() +
                    " 부로 삭제된 계정입니다."); // 계정 만료


        userDTO.setLoginDate(LocalDate.now());
        if (user.isNotification())
            userDTO.setNotification(false);

        userRepository.save(modelMapper.map(userDTO, User.class));
        userSecurityDTO = new UserSecurityDTO(
                user.getUserEmail(),
                user.getPassword(),
                user.getUserName(),
                user.getNickName(),
                user.getPhone(),
                user.getBirthDate(),
                user.getGender(),
                user.isDel(),
                user.getProfileImagePath(),
                user.isSocial(),
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
                ));

        userSecurityDTO.setSocialName(clientName);
        return userSecurityDTO;

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
