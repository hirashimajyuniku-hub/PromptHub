package com.hirashima.prompthub.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hirashima.prompthub.exception.DuplicateEmailException;
import com.hirashima.prompthub.form.SignupForm;
import com.hirashima.prompthub.service.UserService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class SignUpController {

	 private final UserService userService;
	 
    @GetMapping("/signup")
    public String showSignupForm(Model model) {

        model.addAttribute(
                "signupForm",
                new SignupForm()
        );

        return "auth/signup";
    }
    

    @PostMapping("/signup")
    public String signup(
            @Valid
            @ModelAttribute SignupForm signupForm,
            BindingResult result) {

        if(result.hasErrors()) {
            return "auth/signup";
        }
        
        if (!signupForm.isPasswordMatched()) {
            result.rejectValue(
                    "passwordConfirm",
                    "password.mismatch",
                    "パスワードが一致しません"
            );

            return "auth/signup";
        }
        
        
        try {
            userService.signup(signupForm);
        } catch (DuplicateEmailException e) {
            result.rejectValue(
                    "email",
                    "email.duplicate",
                    e.getMessage()
            );

            return "auth/signup";
        }

        return "redirect:/login";
    }
}