package com.example.dayte.security.config;

import com.example.dayte.security.handler.Custom403Handler;
import com.example.dayte.security.handler.CustomSocialLoginHandler;
import com.example.dayte.security.handler.LoginFailHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("===== WebSecurityCustomizer =====");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/members/**","/contents/**", "/WEB-INF/**", "/txt/**", "/images/**", "/js/**","/css/**","/","/error" ,"/temp/**" ,"/notice", "/notice/searchNotices", "/customerService", "/question", "/viewNotice/**", "/event/**", "/mainPostList/").permitAll()
                            .requestMatchers("/admin/**", "/notice/**", "/update/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .formLogin(frm -> {
                    frm.loginPage("/members/login")
                            .loginProcessingUrl("/members/securityLogin")
                            .failureHandler(new LoginFailHandler())
                            .defaultSuccessUrl("/");
                })
                .logout(logout -> {
                    logout.logoutUrl("/members/logout")
                            .logoutSuccessUrl("/members/login")
                            .invalidateHttpSession(true);
                })
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .headers(((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig ->
                        frameOptionsConfig.disable()))
                ).exceptionHandling(expt -> { // Spring Security 예외 처리 구성

                    expt.accessDeniedHandler((accessDeniedHandler()));
                    // accessDeniedHandler() : 사용자의 접근 거부 핸들러를 반환하는 메서드로 인자 값으로 넘겨준다

                })
                .oauth2Login(oauth -> { // OAuth2 로그인을 위한 설정
                    oauth.loginPage("/members/login")
                            .successHandler(authenticationSuccessHandler())
                            .failureHandler(new LoginFailHandler());
                })
        ;

        return http.build();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    // 403 Error 예외처리 메서드
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler(); // : 사용자의 접근 거부 핸들러 반환
    }

    @Bean
    public LoginFailHandler loginFailHandler() {
        return new LoginFailHandler();
    }




}
