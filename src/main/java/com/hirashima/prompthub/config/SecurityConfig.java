package com.hirashima.prompthub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                
                .requestMatchers(
                    "/login",
                    "/signup",
                    "/css/**",
                    "/js/**"
                ).permitAll()

               
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                
                .anyRequest()
                .authenticated()
            )

            .formLogin(form -> form
            	    .loginPage("/login")

            	    .successHandler((request, response, authentication) -> {

            	        boolean isAdmin = authentication.getAuthorities()
            	                .stream()
            	                .anyMatch(authority ->
            	                        authority.getAuthority().equals("ROLE_ADMIN")
            	                );

            	        if (isAdmin) {
            	            response.sendRedirect("/admin");
            	        } else {
            	            response.sendRedirect("/main");
            	        }
            	    })

            	    .permitAll()
            	)
            
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
            );

        

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}