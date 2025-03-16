package com.thymeleaf.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer ->
                        configurer.anyRequest().authenticated()
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
                .authorities("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("emma")
                .password("{noop}stones")
                .authorities("USER", "MANAGER")
                .build();

        UserDetails user3 = User.builder()
                .username("buble")
                .password("{noop}michael")
                .authorities("USER", "MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
