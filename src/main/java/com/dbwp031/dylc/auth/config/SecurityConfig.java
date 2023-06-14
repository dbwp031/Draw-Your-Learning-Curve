package com.dbwp031.dylc.auth.config;

import com.dbwp031.dylc.auth.clients.ClientGithub;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final ClientGithub clientGithub;

    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientGithub.getGithubClientRegistration());
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth -> oauth
                .clientRegistrationRepository(clientRegistrationRepository())
                );

        http.httpBasic(b->b.disable());
        http.csrf(c -> c.disable());
        http.formLogin(f -> f.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**","/js/**").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
