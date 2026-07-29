package com.hirashima.prompthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hirashima.prompthub.service.PromptService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PromptListController {
	
	private final PromptService promptService;
	
	@GetMapping("/prompt-list")
	public String list(Model model) {
		
	    model.addAttribute("prompts", promptService.findAll());

	    return "prompt/list";
	}
}
