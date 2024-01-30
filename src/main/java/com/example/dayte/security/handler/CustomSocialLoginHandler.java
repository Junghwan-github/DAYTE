package com.example.dayte.security.handler;

import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSocialLoginHandler implements AuthenticationSuccessHandler {

    // 소셜 로그인 완료 시 시행되는 핸들러
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        System.out.println("request : " + request);

        System.out.println("response : " + response);

        System.out.println("authentication : " + authentication);


        UserSecurityDTO securityDTO = (UserSecurityDTO) authentication.getPrincipal();
        String userEmail = securityDTO.getUserEmail();


        String encodePw = securityDTO.getPassword();


        // 소셜 로그인 회원 구분할 수 있는 컬럼 새로 추가하고
        // if 문에서 소셜 로그인 구분하는 조건 추가
        // Not Null 필드값을 입력할 수 있도록 유도
        // 만약 전부 수정하지 않고 넘어가게 되면 index 페이지를 제외한 페이지는 볼 수 없도록 제한

        // USER 엔티티의 Not Null 옵션이 부여된 필드를 사용자가 설정하지 않았을 경우
//        if (encodePw.equals("1111") || passwordEncoder.matches("1111", encodePw))
//            // 비밀번호를 변경할 수 있는 창으로 유도
//            response.sendRedirect("/members/joinForm");
//        else
        // index 페이지로 이동
        response.sendRedirect("/");
    }


}
