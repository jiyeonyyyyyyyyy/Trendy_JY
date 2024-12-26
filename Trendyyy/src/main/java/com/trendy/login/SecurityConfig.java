package com.trendy.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    public SecurityConfig(PrincipalOauth2UserService principalOauth2UserService) {
        this.principalOauth2UserService = principalOauth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**").permitAll() // 공개 경로 설정
                .requestMatchers("/login/oauth2/**").permitAll() // OAuth2 로그인 경로
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/", true) // 로그인 성공 시 메인 페이지로 이동
                .failureUrl("/loginFailure") // 로그인 실패 시 이동
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(principalOauth2UserService)
                )
            )
            .formLogin().disable(); // 기본 로그인 페이지 비활성화

        return http.build();
    }
}
