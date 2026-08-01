package com.hirashima.prompthub.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hirashima.prompthub.exception.AccessDeniedException;
import com.hirashima.prompthub.form.PromptForm;
import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.PromptRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;
    
//    プロンプトに追加
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
    
   
    public List<PromptModel> findAll(UserModel loginUser) {


       
        List<PromptModel> prompts =
                promptRepository.findByStatus("PUBLIC");

        List<PromptModel> privatePrompts =
                promptRepository.findByUserAndStatus(
                        loginUser,
                        "PRIVATE"
                );
        
        prompts.addAll(privatePrompts);


        return prompts;
    }
    
    
    
    public PromptModel findById(Long id) {
        return promptRepository.findById(id)
                .orElseThrow();
    }
    
    public void update(Long id,String title,String content,String status,UserModel loginUser
) {
    	
    	PromptModel prompt = promptRepository.findById(id)
                .orElseThrow();
    	
    	if(!prompt.getUser().getId().equals(loginUser.getId())){
    	    throw new AccessDeniedException("編集権限がありません");
    	}
    	
    	prompt.setTitle(title);
        prompt.setContent(content);
        prompt.setStatus(status);
        prompt.setUpdatedAt(LocalDateTime.now());
        
        promptRepository.save(prompt);
    }
    
    
    
    
    
    public void delete(Long id,UserModel loginUser) {
		PromptModel prompt = promptRepository.findById(id)
				.orElseThrow();

		if(!prompt.getUser().getId().equals(loginUser.getId())) {
			throw new AccessDeniedException("削除権限がありません");
		}
    	promptRepository.deleteById(id);
    }
    
    
    
    
    public PromptModel findByIdForEdit(Long id,UserModel loginUser) {
        PromptModel prompt =
                promptRepository.findById(id)
                .orElseThrow();

        
        if(!prompt.getUser()
                .getId()
                .equals(loginUser.getId())) {

            throw new AccessDeniedException(
                    "編集権限がありません!"
            );
        }
        

        return prompt;
    }
    
    public PromptModel findByIdForView(
            Long id,
            UserModel loginUser
    ) {


        PromptModel prompt =
                promptRepository.findById(id)
                .orElseThrow();


        if(
            prompt.getStatus().equals("PRIVATE")
            &&
            !prompt.getUser()
                    .getId()
                    .equals(loginUser.getId())
        ){

            throw new AccessDeniedException(
                    "閲覧権限がありません"
            );
        }


        return prompt;
    }
}
