package com.hirashima.prompthub.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hirashima.prompthub.model.PromptModel;
import com.hirashima.prompthub.model.UserModel;


public interface PromptRepository 
extends JpaRepository<PromptModel, Long> {


List<PromptModel> findByStatus(String status);


List<PromptModel> findByUserAndStatus(
    UserModel user,
    String status
);

}
	

