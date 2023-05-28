package com.dbwp031.dylc.config;

import com.dbwp031.dylc.config.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig{
    private CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(configurer -> configurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .userInfoEndpoint(userinfo -> userinfo.userService(customOAuth2UserService))
                );
        return http.build();
    }
}
