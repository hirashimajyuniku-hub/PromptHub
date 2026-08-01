# PromptHub

## 開発背景

生成AIを利用する中で、便利だったプロンプトがチャット履歴に埋もれ、再利用したいときに探し直す手間を感じることがありました。
また、プロンプトをメモ帳などで保存すると、公開用と個人用の管理や、内容の編集・整理がしづらいという課題があります。
そこで、プロンプトを保存・整理し、必要に応じて他のユーザーへ共有できるWebアプリケーションとしてPromptHubを開発しました。

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
>
> デモアカウント
メールアドレス:test@gmail.com
パスワード:testpass

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


# データベース構成

現在は以下の2テーブルを中心に構成しています。

## users

| カラム名 | 説明 |
|----------|------|
| id | ユーザーID（PK） |
| username | ユーザー名 |
| email | メールアドレス |
| password | ハッシュ化済みパスワード |
| display_name | 表示名 |
| bio | 自己紹介 |
| role | 権限（USER / ADMIN） |
| created_at | 作成日時 |
| updated_at | 更新日時 |

---

## prompts

| カラム名 | 説明 |
|----------|------|
| id | プロンプトID（PK） |
| user_id | 投稿者ID（FK） |
| title | タイトル |
| content | プロンプト本文 |
| status | 公開・非公開 |
| created_at | 作成日時 |
| updated_at | 更新日時 |

---

将来的には

- タグ
- お気に入り
- コメント
- いいね

などのテーブルを追加し、機能を拡張する予定です。

## 苦労した点

### Spring Securityによる認証・認可

Spring Securityを利用し、未ログインユーザー・一般ユーザー・管理者の3種類でアクセス制御を行いました。
また、ログインしていないユーザーはログイン画面へリダイレクトされるよう設定し、権限に応じた画面遷移を実現しています。

### 投稿者のみ編集・削除できる所有者チェック

画面上で編集・削除ボタンを表示しないだけでなく、Service層でもログインユーザーと投稿者を比較し、URLを直接入力した場合でも他人の投稿を編集・削除できないよう実装しました。

### 独自例外によるエラー処理

メールアドレス重複や権限エラーなどを独自例外として定義し、ControllerAdviceで一元管理しました。
例外の種類ごとに適切なエラーページやメッセージを表示できるようにすることで、保守性と可読性を向上させています。

### 責務分離を意識した設計

Controller・Service・Repository・Modelの役割を明確に分けています。
画面遷移、業務処理、データベースアクセスを分離することで、保守しやすい構成を意識しました。
