package com.example.slstore.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Profile("prod")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        @Qualifier("adminAuthenticationProvider")
        private AuthenticationProvider adminAuthenticationProvider;

        @Autowired
        @Qualifier("customerAuthenticationProvider")
        private AuthenticationProvider customerAuthenticationProvider;

        @Bean
        @Order(1)
        public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
                http.securityMatcher("/admin/**")
                        .authenticationProvider(adminAuthenticationProvider)
                        .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/admin/login").permitAll()
                                .requestMatchers("/admin/**").authenticated()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        ).formLogin(form -> form
                                .loginPage("/admin/login")
                                .defaultSuccessUrl("/admin/dashboard", true)
                                .permitAll()
                        ).logout(logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                                .logoutSuccessUrl("/admin/login")
                                .permitAll()
                        );

                return http.build();
        }

        @Bean
        @Order(2)
        public SecurityFilterChain customerFilterChain(HttpSecurity http) throws Exception {
                http.securityMatcher("/**")
                        .authenticationProvider(customerAuthenticationProvider)
                        .authorizeHttpRequests(authz -> authz
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                .permitAll()
                                // 顧客側の設定
                                .requestMatchers("/mypage/**").authenticated()
                                .requestMatchers("/shop/**").authenticated()
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                        ).formLogin(form -> form
                                .loginPage("/customer/login")
                                .usernameParameter("email")
                                // .defaultSuccessUrl("/mypage", true)
                                .permitAll()
                        ).logout(logout -> logout
                                .logoutSuccessUrl("/test")
                                .permitAll()
                        );

                return http.build();
        }

}
