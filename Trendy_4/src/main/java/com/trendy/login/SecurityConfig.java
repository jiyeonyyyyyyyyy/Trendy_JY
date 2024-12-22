package com.trendy.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**").permitAll()
                // 카카오 OAuth2 로그인 엔드포인트는 permitAll
                .requestMatchers("/login/oauth2/**").permitAll()
                // 나머지는 인증 필요
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(principalOauth2UserService)
                )
            )
            .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
