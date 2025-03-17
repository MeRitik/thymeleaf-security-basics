package com.thymeleaf.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@EnableMethodSecurity // For @PreAuthorize
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer ->
                        configurer
                                .requestMatchers("/").hasRole("USER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .anyRequest().authenticated()
        ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/handleLogin")
                        .permitAll()
        ).logout(
                LogoutConfigurer::permitAll
        );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.builder()
                .username("john")
                .password("{noop}cenaaa")
                .authorities("ROLE_USER")
                .build();

        UserDetails user2 = User.builder()
                .username("emma")
                .password("{noop}stones")
                .authorities("ROLE_USER", "ROLE_MANAGER")
                .build();

        UserDetails user3 = User.builder()
                .username("buble")
                .password("{noop}michael")
                .authorities("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
