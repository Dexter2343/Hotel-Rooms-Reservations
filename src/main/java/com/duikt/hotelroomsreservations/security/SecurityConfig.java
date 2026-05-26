package com.duikt.hotelroomsreservations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()

                        .requestMatchers(POST, "/api/users").permitAll()

                        .requestMatchers(PUT, "/api/users/{id}").hasRole("ADMIN")
                        .requestMatchers(DELETE, "/api/users/{id}").hasRole("ADMIN")
                        .requestMatchers(GET, "/api/users").hasRole("ADMIN")

                        .requestMatchers(PUT, "/api/rooms/*/users/*/buy")
                        .hasRole("USER")

                        .requestMatchers(PUT, "/api/rooms/*/users/*/buy-with-discount")
                        .hasRole("USER")

                        .requestMatchers(GET, "/api/additional-services/buy/{serviceId}/room/{roomId}/user/{userId}")
                        .hasRole("USER")

                        .requestMatchers(POST, "/api/rooms")
                        .hasRole("ADMIN")

                        .requestMatchers(PUT, "/api/rooms/{id}")
                        .hasRole("ADMIN")

                        .requestMatchers(DELETE, "/api/rooms/{id}")
                        .hasRole("ADMIN")

                        .requestMatchers(PUT, "/api/rooms/{roomId}/discount")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
               .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}