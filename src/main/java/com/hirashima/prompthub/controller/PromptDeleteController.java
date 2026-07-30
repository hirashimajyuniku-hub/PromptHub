package com.hirashima.prompthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hirashima.prompthub.service.PromptService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptDeleteController {

    private final PromptService promptService;

    @PostMapping("/prompt/{id}/delete")
    public String delete(@PathVariable Long id) {

        promptService.delete(id);

        return "redirect:/prompt-list";
    }
}