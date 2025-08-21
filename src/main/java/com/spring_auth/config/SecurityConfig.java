package com.spring_auth.config;

import com.spring_auth.config.filter.JwtAuthFilter;
import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.kakao.service.KakaoVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final KakaoVerifyService kakaoVerifyService;
    private final EmployeeRepository employeeRepository;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    private static final String[] AUTH_ALLOWLIST = {
            "/swagger-ui/**", "/v3/**", "/login/**", "/images/**", "/kakao/**"
    };


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*여기가 일반적인 restApi 설정, 추후 필*/
        http.csrf(s -> s.disable())
                .cors(s -> s.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(f -> f.disable());

        http.addFilterBefore(new JwtAuthFilter(kakaoVerifyService, employeeRepository), UsernamePasswordAuthenticationFilter.class);
        //이거 넣으면 모든 url에 토큰 검사함
        http.authorizeHttpRequests(a -> a
                                      .requestMatchers(AUTH_ALLOWLIST)
                                      .permitAll()
                                      .anyRequest()
                                      .authenticated()
                                    );

        //에러시 처리
        http.exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }
}
