package com.hirashima.prompthub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hirashima.prompthub.form.PromptForm;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.UserRepository;
import com.hirashima.prompthub.service.PromptService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptController {
	
	private final UserRepository userRepository;
	private final PromptService promptService;
	
    @GetMapping("/prompt/create") 
    public String create(Model model) {
    	
    	model.addAttribute("promptForm", new PromptForm());
        return "prompt/create";

    }
    
    
    
    @PostMapping("/prompt/create")
    public String create(
            @ModelAttribute PromptForm form,
            Authentication authentication
    ) {
    	
    	String email = authentication.getName();
    	
    	UserModel user = userRepository.findByEmail(email).orElseThrow();
    	
    	promptService.create(form, user);
    	return "redirect:/prompt-list";
    }
    

}
