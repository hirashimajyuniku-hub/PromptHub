// Userテーブルを操作するRepository
// DBへの保存・検索などを担当します。

package com.hirashima.prompthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hirashima.prompthub.model.User;


// Userエンティティ用Repository
// JpaRepositoryを継承することでCRUD機能が使える
public interface UserRepository extends JpaRepository<User, Long> {

}