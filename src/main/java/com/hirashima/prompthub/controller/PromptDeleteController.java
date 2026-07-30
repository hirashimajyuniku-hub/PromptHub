package com.hirashima.prompthub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.service.PromptService;
import com.hirashima.prompthub.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PromptDeleteController {

    private final PromptService promptService;
    private final UserService userService;

    @PostMapping("/prompt/{id}/delete")
    public String delete(
            @PathVariable Long id,
            Authentication authentication
    ) {
        UserModel loginUser =
                userService.findByEmail(authentication.getName());

        promptService.delete(id, loginUser);

        return "redirect:/prompt-list";
    }
    }