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

    //토큰 없이 접근 예외 처리
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    //권한 예외 처리
    private final CustomAccessDeniedHandler accessDeniedHandler;

    private static final String[] AUTH_ALLOWLIST = {
            "/swagger-ui/**", "/v3/**", "/login/**", "/images/**", "/kakao/**"
    };


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*여기가 일반적인 restApi 설정, 추후 필요 로직 알아서 추가*/
        http.csrf(s -> s.disable())
                .cors(s -> s.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(f -> f.disable());

        //이거 넣으면 모든 url에 토큰 검사함
        http.authorizeHttpRequests(a -> a
                                      .requestMatchers(AUTH_ALLOWLIST).permitAll()
                                      .requestMatchers("/admin/**").hasRole("ADMIN")
                                      .requestMatchers("/employees/**").hasRole("USER")
                                      .requestMatchers("/departments/**").hasRole("USER")
                                      .anyRequest().authenticated()
                                    );

        //토큰 검사 및 권한 부여
        http.addFilterBefore(new JwtAuthFilter(kakaoVerifyService, employeeRepository), UsernamePasswordAuthenticationFilter.class);
        //토큰 없이 접근 예외 처리, 권한 에러시 처리
        http.exceptionHandling(e ->
                 e.authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        return http.build();
    }
}
