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
                // ログイン画面、新規登録は誰でもアクセス可能
                .requestMatchers("/login", "/signup").permitAll()
                // それ以外はログイン必須
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // 自作ログイン画面
                .loginPage("/login")

                // ログイン成功後
                .defaultSuccessUrl("/main", true)

                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
            );
//        いずれ論理削除に挑戦

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}