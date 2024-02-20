package com.example.dayte.members.persistence;

import com.example.dayte.admin.mianslider.domain.VisitorStatistics;
import com.example.dayte.admin.mianslider.persistence.VisitorStatisticsRepository;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VisitorStatisticsRepository visitorStatisticsRepository;

    @Test
    public void insertDate(){
        LocalDate date = LocalDate.now().minusYears(1);

        IntStream.rangeClosed(0, 365).forEach(i -> {
            double random = Math.random();
            int intValue = (int)(random * 1000) + 1000;
            VisitorStatistics visitorStatistics = VisitorStatistics.builder()
                    .id(i)
                    .date(date.plusDays(i))
                    .visitors(intValue)
                    .build();
            visitorStatisticsRepository.save(visitorStatistics);
        });

    }

    @Test
    public void insertTenUser() {

        IntStream.rangeClosed(1, 3).forEach(i -> {
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
                    .userEmail("fpdlan12@test.com")
                    .password(passwordEncoder.encode("!!test01"))
                    .userName("테스트어드민")
                    .nickName("OhyeonTest")
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

    @Test
    public void insertPostDate() {

        IntStream.rangeClosed(31, 100).forEach(i -> {
            Post post;
            if(i < 66) {
                post = Post.builder()
                        .title("실험용으로 양산한 포스트 " + i)
                        .content("실험용으로 양산한 포스트 내용 " + i)
                        .user(userRepository.findById("jungie2@naver.com").get())
                        .build();
            } else {
                post = Post.builder()
                        .title("실험용으로 양산한 포스트 " + i)
                        .content("실험용으로 양산한 포스트 내용 " + i)
                        .user(userRepository.findById("fpdlan12@naver.com").get())
                        .build();
            }

            postRepository.save(post);

        });


    }

}