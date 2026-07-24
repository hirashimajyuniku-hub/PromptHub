// Userクラス
// PostgreSQLのusersテーブルと対応するEntityです。
// ユーザー情報（名前・メールアドレス・パスワード）を管理します。

package com.hirashima.prompthub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {

    // ユーザーID
    // DB側で自動的に番号を振ります
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ユーザー名
    private String username;


    // メールアドレス
    private String email;


    // パスワード
    private String password;


    // getter / setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
