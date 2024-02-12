package com.niksan.niksansocialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth
                                            .requestMatchers("/api/**")
                                            .authenticated()
                                            .anyRequest()
                                            .permitAll())
                                            .httpBasic()
                                            .and()
                                            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
