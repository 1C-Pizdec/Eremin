# Как MAX™, только MIN™

Crud-сервис постов.

## Run
```sh
cd min # перейдите в проект

./gradlew build # сборка
./gradlew bootRun # запуск
```

## В браузере

Все посты
```sh
http://localhost:8080/api/v1/posts
```

Один пост по ID
```sh
http://localhost:8080/api/v1/posts/1
```

## БД
```sh
http://localhost:8080/h2 # JDBC URL: jdbc:h2:mem:testdb
```
