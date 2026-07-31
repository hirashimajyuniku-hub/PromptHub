package com.hirashima.prompthub.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserRepository userRepository;

    @ModelAttribute("loginUser")
    public UserModel loginUser(Principal principal) {

        // ログインしていない画面ではnullを返す
        if (principal == null) {
            return null;
        }

        // principal.getName()にはログイン中ユーザーのメールアドレスが入る
        return userRepository.findByEmail(principal.getName())
                .orElse(null);
    }
}