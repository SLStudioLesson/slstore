package com.example.slstore.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.slstore.common.security.AdminDetailsService;
import com.example.slstore.common.security.LoginUserDetailsService;

@Configuration
public class AuthenticationConfig {

    private final AdminDetailsService adminDetailsService;

    private final LoginUserDetailsService loginUserDetailsService;

    public AuthenticationConfig(AdminDetailsService adminDetailsService,
            LoginUserDetailsService loginUserDetailsService) {
        this.adminDetailsService = adminDetailsService;
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @Bean
    public AuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationProvider customerAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}