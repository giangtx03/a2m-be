package com.a2m.project.configs;

import com.a2m.project.configs.filters.CorsFilter;
import com.a2m.project.configs.filters.JwtTokenFilter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Value("${api.prefix}")
    private String apiPrefix;


    private String[] whiteList;
    @PostConstruct
    public void init() {
        whiteList = new String[] {
                String.format("public/%s/products/**", apiPrefix),
                String.format("public/%s/users/**", apiPrefix),
                String.format("public/%s/categories/**", apiPrefix)
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(request ->{
                request
                        .requestMatchers(
                                whiteList
                        )
                        .permitAll()
                        .anyRequest().authenticated();
            })
            .cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
