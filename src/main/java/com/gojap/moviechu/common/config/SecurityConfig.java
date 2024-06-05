package com.gojap.moviechu.common.config;

import com.gojap.moviechu.auth.jwt.CustomJwtAuthenticationEntryPoint;
import com.gojap.moviechu.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChainDev(HttpSecurity http) throws Exception {
        setHttp(http);
        return http.build();
    }

    private void setHttp(HttpSecurity http) throws Exception {
        authorizeHttpRequests(http);
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(customJwtAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void authorizeHttpRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/auth", "POST")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                        .anyRequest().authenticated()
        );
    }
}
