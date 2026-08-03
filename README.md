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

## 工夫した点

### ログイン状態や権限による画面の制御

Spring Securityを使い、ログインしていない人・一般ユーザー・管理者で利用できる機能を分けました。ログインしていない場合はログイン画面へ移動するようにし、権限に応じて使える画面を制御しています。

### 投稿者本人だけが編集・削除できるようにしたこと

編集・削除ボタンを表示しないだけではなく、プログラム側でも投稿者本人かどうかを確認しています。URLを直接入力した場合でも、他の人の投稿を編集・削除できないようにしました。

### エラー処理をまとめて管理

メールアドレスの重複や権限エラーなどをまとめて管理し、エラーが起きたときに分かりやすいメッセージを表示できるようにしました。処理を一か所にまとめることで、後から修正しやすいように工夫しています。

### 役割ごとに処理を分けた設計

画面の処理、データの処理、データベースとのやり取りなど、それぞれの役割を分けて実装しました。処理を整理することで、コードが読みやすく修正しやすい構成を意識しました。

## 難しかったこと

### データの流れを理解すること

最初は、画面で入力した内容がどのようにController、Service、Repositoryを通ってデータベースに保存され、再び画面に表示されるのかという一連の流れを理解するのが難しかったです。

### オブジェクトやインスタンスの考え方

Javaでは自分でインスタンスを作成する場面だけでなく、Springが自動で生成してくれることも多くありました。どこで自分が作成していて、どこでSpringが管理しているのかを理解するまで苦労しました。
画面遷移、業務処理、データベースアクセスを分離することで、保守しやすい構成を意識しました。
