package com.spring_auth.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthFilter extends OncePerRequestFilter  {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.hasText(authorization)) {
            TestingAuthenticationToken authentication = new TestingAuthenticationToken("username", "password", "ROLE_TEST");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //filterChain.doFilter이 없으면 다음으로 넘어 갈수 없다.
        filterChain.doFilter(request, response);
    }
}
