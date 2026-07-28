// DBのユーザー情報をSpring Securityが使える形に変換するクラス

package com.hirashima.prompthub.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    // Spring Securityがログイン時に呼び出すメソッド
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {


        // DBからメールアドレスでユーザー検索
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException(
                        "ユーザーが見つかりません"
                    )
                );


        // UserModelをSpring Security用のUserDetailsへ変換
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}