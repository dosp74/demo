package com.example.demo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig {
    private final String[] allowUris = {
            "/sign-up",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v3/api-docs/**",
    };

    // SecurityFilterChain 정의
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 접근 제어 설정
                .authorizeHttpRequests(requests -> requests
                        // 특정 URL 패턴에 대한 접근 권한 설정
                        .requestMatchers(allowUris).permitAll() // 인증 없이 접근 가능한 경로 지정
                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN") // 'ADMIN' 역할을 가진 사용자만 접근 가능하도록 제한
                        // 그외 모든 요청에 대해 인증을 요구함
                        .anyRequest().authenticated()
                )
                // 폼 기반 로그인에 대한 설정
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        // 로그인 페이지는 모든 사용자가 접근 가능하도록 설정
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                // 로그아웃 처리에 대한 설정
                .logout(logout -> logout
                        // /logout 경로로 로그아웃을 처리
                        .logoutUrl("/logout")
                        // 로그아웃 성공 시 /login?logout으로 리다이렉트
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
