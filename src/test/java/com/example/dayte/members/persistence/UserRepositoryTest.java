package com.example.dayte.members.persistence;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTenUser() {

        IntStream.rangeClosed(1, 9).forEach(i -> {
            User user = User.builder()
                    .userEmail("test" + i + "@test.com")
                    .password(passwordEncoder.encode(i+""+i+""+i+""+i+""+i+""+i+""+i+""+i))
                    .userName("테스트" + i)
                    .nickName("testNickName" + i)
                    .phone("010-"+i+""+i+""+i+""+i+"-"+i+""+i+""+i+""+i)
                    .birthDate("2000101"+i)
                    .gender("other")
                    .role(RoleType.USER)
                    .del(false)
                    .social(false)
                    .profileImageName("default_icon_profile.png")
                    .profileImagePath("/images/default_icon_profile.png")
                    .build();
            userRepository.save(user);
        });

    }

    @Test
    public void insertOneAdmin() {

            User user = User.builder()
                    .userEmail("testAdmin@test.com")
                    .password(passwordEncoder.encode("qweqweqwe"))
                    .userName("테스트어드민")
                    .nickName("testAdminNickName")
                    .phone("010-1010-0101")
                    .birthDate("20001011")
                    .gender("other")
                    .role(RoleType.ADMIN)
                    .del(false)
                    .social(false)
                    .profileImageName("default_icon_profile.png")
                    .profileImagePath("/images/default_icon_profile.png")
                    .build();
            userRepository.save(user);


    }

}