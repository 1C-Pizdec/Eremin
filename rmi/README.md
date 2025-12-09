## RMI "Решатель простых выражений"

Удалённый вызов методов (RMI) на Java. Выполняется удалённая передача объекта OperationDTO, который инкапсулирует в себя операцию

## Run Server

```sh
cd rmi # перейдите в папку проекта

find src -name "*.java" | xargs javac -d bin # компиляция проекта

java -cp bin ru.pechenkindd.server.Server 1099 # запуск сервера на порту 8080

java -cp bin ru.pechenkindd.client.Client localhost 1099 # запуск клиента для его подключения к localhost 8080
```
