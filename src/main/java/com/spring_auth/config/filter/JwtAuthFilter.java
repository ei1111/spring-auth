package com.spring_auth.config.filter;

import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.kakao.service.KakaoVerifyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter  {
    private final KakaoVerifyService kakaoVerifyService;
    private final EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            String accessToken = authorization.substring(7);

            String nickname = kakaoVerifyService.getKakaoUserInfo(accessToken).getKakaoAccount().getProfile().getNickname();

            if (employeeRepository.existsEmployeeByKakoNickName(nickname)) {
                Employee employee = employeeRepository.findByKakoNickName(nickname).orElseThrow(() -> new UsernameNotFoundException("User not found"));

                List<GrantedAuthority> authorities = getAuthorities(employee);

                TestingAuthenticationToken authentication = new TestingAuthenticationToken(employee.getFirstName(), "password", authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //filterChain.doFilter이 없으면 다음으로 넘어 갈수 없다.
        filterChain.doFilter(request, response);
    }

    private  List<GrantedAuthority> getAuthorities(Employee employee) {
        return employee.isHR() ?
                   List.of(
                           new SimpleGrantedAuthority("ROLE_USER"),
                           new SimpleGrantedAuthority("ROLE_ADMIN")
                   )
                :  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
