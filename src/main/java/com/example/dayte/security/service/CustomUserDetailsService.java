package com.example.dayte.security.service;


import com.example.dayte.members.domain.User;
import com.example.dayte.security.dto.UserSecurityDTO;
import com.example.dayte.members.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=========================loadUserByUsername============" + username + "=====");

        System.out.println("email : " + username);
        // 요청 아이디에 해당하는 회원이 있는지 조회 (DB => domain.User)
        Optional<User> result = userRepository.findById(username);

        if (result.isEmpty())  // 해당 아이디를 가진 사용자가 없다면
            throw new UsernameNotFoundException("username 에 해당하는 회원이 없다...");

        // user = DB 에서 조회해 온 회원의 엔티티
        User user = result.get();
        user.setLoginDate(LocalDate.now());
        userRepository.save(user);
        // 전송 객체인 DTO 를 이용하여 회원 정보 반환
        return new UserSecurityDTO(
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
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))

                // List 컬렉션을 하나 생성해서 1. ROLE_USER, 2. ROLE_ADMIN
        );

    }


}



