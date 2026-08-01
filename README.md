# PromptHub

AIプロンプトを保存・整理・共有できる
プロンプト管理Webアプリケーション
ChatGPTなどの生成AIを利用する中で、作成したプロンプトがチャット履歴に埋もれ、再利用しづらいという悩みを解決するために開発しました。
公開・非公開の設定や投稿の編集・削除機能を備えています。

---
## アプリ画面
### トップページ
<img width="1915" height="952" alt="スクリーンショット 2026-08-02 000306" src="https://github.com/user-attachments/assets/697d81a3-6b07-40d9-a4ff-58427558eb38" />

### 一覧画面
<img width="1912" height="952" alt="image" src="https://github.com/user-attachments/assets/b3415a29-538a-4e10-8dc5-fa50a3569220" />

### 詳細画面
<img width="1917" height="956" alt="image" src="https://github.com/user-attachments/assets/bdf7ce78-276e-4397-9da2-41dfa9541cff" />

### 投稿画面
<img width="1915" height="948" alt="スクリーンショット 2026-08-02 005411" src="https://github.com/user-attachments/assets/7b6607d9-9410-4b78-9509-8e262f6b2bce" />

### ログイン画面
<img width="1908" height="950" alt="image" src="https://github.com/user-attachments/assets/8f523e89-c241-41ee-845c-3878c7002b30" />

## URL
### アプリ

> https://prompthub-y4kd.onrender.com/login

### GitHub

> https://github.com/hirashimajyuniku-hub/PromptHub

# 使用技術

| 分類 | 技術 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot |
| MVC | Spring MVC |
| Security | Spring Security |
| ORM | Spring Data JPA |
| Validation | Bean Validation |
| View | Thymeleaf |
| Database | PostgreSQL |
| Build Tool | Maven |
| Container | Docker |
| IDE | Eclipse |


# 機能一覧

| 機能 | 内容 |
|------|------|
| ユーザー登録 | 新規アカウント作成 |
| ログイン | Spring Securityによる認証 |
| ログアウト | セッション終了 |
| プロンプト投稿 | 新規投稿 |
| 一覧表示 | 投稿一覧表示 |
| 詳細表示 | 投稿詳細表示 |
| 編集 | 投稿者のみ編集可能 |
| 削除 | 投稿者のみ削除可能 |
| 公開・非公開 | 公開範囲の切り替え |
| メール重複チェック | 同一メールアドレスの登録防止 |
| エラー画面 | 権限エラーなどを表示 |
