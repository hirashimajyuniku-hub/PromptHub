package com.hirashima.prompthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.service.PromptService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptEditController {
	
	private final PromptService promptService;
	
	@GetMapping("/prompt/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {

        PromptModel prompt = promptService.findById(id);

        model.addAttribute("prompt", prompt);

        return "prompt/edit";
}
	@PostMapping("/prompt/{id}/edit")
	public String update(
	        @PathVariable Long id,
	        @RequestParam String title,
	        @RequestParam String content,
	        @RequestParam String status
	) {

	    promptService.update(id, title, content, status);

	    return "redirect:/prompt/" + id;
	}
	
}