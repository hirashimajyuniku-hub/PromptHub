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

   
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {


        
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException(
                        "ユーザーが見つかりません"
                    )
                );


       
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
    
    
}