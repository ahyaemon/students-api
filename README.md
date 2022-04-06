# students-api

## 動作確認手順

### 1. docker image の作成

```shell
./gradlew bootBuildImage
```

※ mac での動作確認済み。windows の場合は gradlew の代わりに gradlew.bat を使用する

### 2. docker container の起動

```shell
docker run -p "8080:8080" student-api:1.0.0
```

### 3. curl やブラウザアクセスなどで確認

起動時に INSERT されるデータは `src/main/resources/data.sql` に書いています。

- 先生のIDのみ指定

```shell
curl 'http://localhost:8080/students?teacherId=1'
```

- page, limit, name_like を指定

```shell
curl 'http://localhost:8080/students?teacherId=1&page=2&limit=2&name_like=tanaka'
```

- loginId_like を指定

```
curl 'http://localhost:8080/students?teacherId=1&loginId_like=ab'
```

## アーキテクチャ

- 言語
  - Kotlin
- フレームワーク
  - SpringBoot
- DB アクセス
  - jOOQ
- 設計思想
  - DDD, CleanArchitecture をかいつまんで実装
    - 他に依存しない domain 層を作り、application 層のユースケースが依存する
    - adapter には外界（DB）とやりとりするクラスを置き、application 層にある port を実装する

## やっていないこと
- 環境によるプロファイルの設定、ログレベルの設定
- TDD
- 負の値のチェックや桁数などのバリデーション（nullチェック、数値チェックは作成）
- エラー時のレスポンスの設計
