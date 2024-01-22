package com.example.projectt.security.config;

import com.example.projectt.members.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                    auth.requestMatchers("/members/**", "/WEB-INF/**", "/txt/**", "/images/**", "/js/**","/css/**","/","/error").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(frm -> {
                    frm.loginPage("/members/login")
                            .loginProcessingUrl("/members/securitylogin")
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
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }





}
