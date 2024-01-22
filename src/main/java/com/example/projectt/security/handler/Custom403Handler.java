package com.example.projectt.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// Spring Security 접근 거부 (403 Forbidden) 상황을 처리하는 사용자 정의 핸들러 클래스 구현
@Log4j2
public class Custom403Handler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("===================  ACCESS DENIED  ===================");

        // HTTP 응답 상태를 403 forbidden으로 설정
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // JSON 요청이었는지 확인
        String contentType = request.getHeader("Content-Type");
        boolean jsonRequest = contentType.startsWith("application/json");
        log.info("isJSON : " + jsonRequest);

        // 일반 request = 즉, JSON 요청이 아닌 경우
        if(!jsonRequest) {
            // 사용자를 로그인 페이지로 리다이렉션함 URL에 'error = ACCESS_DENIED' 쿼리 파라미터를 추가하여 접근 거부 상황을 알림.
            response.sendRedirect("/members/login?error=ACCESS_DENIED");
        }

    }
}
