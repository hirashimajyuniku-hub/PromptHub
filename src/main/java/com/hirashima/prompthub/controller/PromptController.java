package com.hirashima.prompthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromptController {
	
    @GetMapping("/prompt/create")
    public String create() {

        return "prompt/create";

    }

}
