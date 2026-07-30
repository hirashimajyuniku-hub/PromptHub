package com.hirashima.prompthub.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.service.PromptService;
import com.hirashima.prompthub.service.UserService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class PromptEditController {
	
	private final PromptService promptService;
	private final UserService userService;
	
	@GetMapping("/prompt/{id}/edit")
	public String edit(@PathVariable Long id, Model model,Authentication authentication) {

		
	    UserModel loginUser =
	            userService.findByEmail(
	                    authentication.getName()
	            );
	    
	    PromptModel prompt =
	            promptService.findByIdForEdit(
	                    id,
	                    loginUser
	            );
	    
	    
        model.addAttribute("prompt", prompt);

        return "prompt/edit";
}
	
	
	
	@PostMapping("/prompt/{id}/edit")
	public String update(
	        @PathVariable Long id,
	        @RequestParam String title,
	        @RequestParam String content,	
	        @RequestParam String status,
	        Authentication authentication
	) {
		
	    UserModel loginUser =
	            userService.findByEmail(
	                    authentication.getName()
	            );

	    promptService.update(id, title, content, status,loginUser);

	    return "redirect:/prompt/" + id;
	}
	
}