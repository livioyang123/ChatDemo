package com.chat.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // disattiva CSRF per test POST
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll()); // permette tutte le richieste

        return http.build();
    }
}