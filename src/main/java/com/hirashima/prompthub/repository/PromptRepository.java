package com.hirashima.prompthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hirashima.prompthub.model.PromptModel;

// Promptテーブルを操作するRepository
public interface PromptRepository extends JpaRepository<PromptModel, Long> {

}