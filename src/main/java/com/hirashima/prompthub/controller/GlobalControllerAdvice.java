package com.hirashima.prompthub.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hirashima.prompthub.exception.AccessDeniedException;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserRepository userRepository;

    @ModelAttribute("loginUser")
    public UserModel loginUser(Principal principal) {

        if (principal == null) {
            return null;
        }

        return userRepository.findByEmail(principal.getName())
                .orElse(null);
    }
    
    
    

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(
            AccessDeniedException e,
            Model model) {

        model.addAttribute("message", e.getMessage());

        return "error/403";
    }
}