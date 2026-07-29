package com.hirashima.prompthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.service.PromptService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptDetail {

    private final PromptService promptService;

    @GetMapping("/prompt/{id}")
    public String detail(@PathVariable Long id, Model model) {

        PromptModel prompt = promptService.findById(id);

        model.addAttribute("prompt", prompt);

        return "prompt/detail";
    }
}