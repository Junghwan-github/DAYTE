package com.example.dayte.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Log4j2
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        log.info("======== login fail handler ========");

        String errorMessage;
        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
        } else if (e instanceof UsernameNotFoundException){
            errorMessage = "존재하지 않는 아이디 입니다.";
        } else if (e instanceof DisabledException) {
            errorMessage = "귀하의 계정은 휴면계정입니다.";
        } else if (e instanceof LockedException) {
            errorMessage = "귀하의 계정은 정지된 계정입니다.";
        } else if (e instanceof AccountExpiredException) {
            errorMessage = "귀하의 계정의 삭제된 계정입니다.";
        } else {
            errorMessage = "알 수 없는 이유로 로그인이 안되고 있습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); // 한글 인코딩 깨짐 방지

        setDefaultFailureUrl("/members/login?hasMessage=true&errorMessage=" + errorMessage);
        super.onAuthenticationFailure(request, response, e);
    }
}
