package com.hirashima.prompthub.service;
import java.time.LocalDateTime;
import java.util.List;

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
        
        prompt.setCreatedAt(LocalDateTime.now());
        
        prompt.setUpdatedAt(LocalDateTime.now());

        promptRepository.save(prompt);

    }
    
    public List<PromptModel> findAll() {
        return promptRepository.findAll();
    }
    
    public PromptModel findById(Long id) {
        return promptRepository.findById(id)
                .orElseThrow();
    }
    
    public void update(Long id,String title,String content,String status) {
    	PromptModel prompt = promptRepository.findById(id)
                .orElseThrow();
    	
    	prompt.setTitle(title);
        prompt.setContent(content);
        prompt.setStatus(status);
        prompt.setUpdatedAt(LocalDateTime.now());
        
        promptRepository.save(prompt);
    }
    
    public void delete(Long id) {
    	promptRepository.deleteById(id);
    }
}
