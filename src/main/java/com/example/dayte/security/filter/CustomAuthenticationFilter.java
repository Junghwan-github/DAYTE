package com.example.dayte.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class CustomAuthenticationFilter extends AuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationConverter authenticationConverter) {
        super(authenticationManager, authenticationConverter);
    }

    public CustomAuthenticationFilter(AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver, AuthenticationConverter authenticationConverter) {
        super(authenticationManagerResolver, authenticationConverter);
    }


}
