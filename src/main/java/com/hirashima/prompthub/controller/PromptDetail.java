package com.hirashima.prompthub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.service.PromptService;
import com.hirashima.prompthub.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptDetail {
	private final UserService userService;
    private final PromptService promptService;

    @GetMapping("/prompt/{id}")
    public String detail(@PathVariable Long id, 
    					Model model,
    					Authentication authentication) {
    	
        UserModel loginUser =
                userService.findByEmail(authentication.getName());
    	
    	
    	   PromptModel prompt =
    	            promptService.findByIdForView(id,loginUser);
        
        
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("prompt", prompt);
        model.addAttribute("loginUser", loginUser);

        return "prompt/detail";
    }
}