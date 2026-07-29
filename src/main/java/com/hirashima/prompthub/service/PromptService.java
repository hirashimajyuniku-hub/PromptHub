package com.hirashima.prompthub.service;

import org.springframework.stereotype.Service;

import com.hirashima.prompthub.form.PromptForm;
import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.PromptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;
    
    public void create(PromptForm form, UserModel user) {

        PromptModel prompt = new PromptModel();

        prompt.setUser(user);

        prompt.setTitle(form.getTitle());

        prompt.setContent(form.getContent());

        prompt.setStatus(form.getStatus());

        promptRepository.save(prompt);

    }
}
