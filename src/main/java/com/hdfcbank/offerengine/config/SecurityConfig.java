package com.hdfcbank.offerengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/labels").hasRole("LABEL_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/labels/**").hasRole("LABEL_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/labels/**").hasRole("LABEL_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customer-inquiry").hasAnyRole("MERCHANT_CHANNEL", "LABEL_ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
