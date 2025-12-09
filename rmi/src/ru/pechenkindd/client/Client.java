package ru.pechenkindd.client;

import ru.pechenkindd.rmi.contract.ExpressionExecutor;
import ru.pechenkindd.rmi.contract.OperationDTO;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static final String BINDING_NAME = "server.expression_executor";

    public static void main(String[] args) {

        try {
            // Хост и порт RMI-сервера
            String host = "localhost";
            int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

            // Получаем реестр
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Находим удалённый объект
            ExpressionExecutor executor = (ExpressionExecutor) registry.lookup(BINDING_NAME);

            // Создаём DTO операции
            OperationDTO op1 = new OperationDTO("add", 5.0, 3.0);
            OperationDTO op2 = new OperationDTO("mul", 10.0, 4.0);

            // Вызываем удалённый метод
            Double result1 = executor.execStep(op1);
            Double result2 = executor.execStep(op2);

            // Выводим результат
            System.out.println("5 + 3 = " + result1);
            System.out.println("10 * 4 = " + result2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
