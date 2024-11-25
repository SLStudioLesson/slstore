package com.example.slstore.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.slstore.common.security.AdminDetailsService;
import com.example.slstore.common.security.CustomerDetailsService;

@Configuration
public class AuthenticationConfig {

    private final AdminDetailsService adminDetailsService;

    private final CustomerDetailsService customerDetailsService;

    public AuthenticationConfig(AdminDetailsService adminDetailsService,
            CustomerDetailsService customerDetailsService) {
        this.adminDetailsService = adminDetailsService;
        this.customerDetailsService = customerDetailsService;
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
        provider.setUserDetailsService(customerDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}