package com.hirashima.prompthub.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.service.PromptService;
import com.hirashima.prompthub.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptListController {
	
	private final PromptService promptService;
	private final UserService userService;
	
	@GetMapping("/prompt-list")
	public String list(
	        Model model,
	        Authentication authentication
	){

	    UserModel loginUser =
	            userService.findByEmail(
	                    authentication.getName()
	            );


	    List<PromptModel> prompts =
	            promptService.findAll(loginUser);


	    model.addAttribute(
	            "prompts",
	            prompts
	    );


	    return "prompt/list";
	}
}
