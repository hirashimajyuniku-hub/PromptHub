// Userテーブルを操作するRepository
package com.hirashima.prompthub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hirashima.prompthub.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // メールアドレスでユーザーを検索
    Optional<UserModel> findByEmail(String email);

}
