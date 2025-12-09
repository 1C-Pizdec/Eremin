package ru.pechenkindd.client;

import ru.pechenkindd.rmi.contract.ExpressionExecutor;
import ru.pechenkindd.rmi.contract.OperationDTO;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    public static final String BINDING_NAME = "server.expression_executor";

    public static void main(String[] args) {

        int port = 8080;
        String host = "localhost";
        if (args.length == 2) {
            try {
                port = Integer.parseInt(args[1].trim());
                host = args[0].trim();
            } catch (NumberFormatException e) {
                System.err.println("Используйте следующие аргументы: <host> <port>");
                System.exit(1);
            }
        }

        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ExpressionExecutor executor = (ExpressionExecutor) registry.lookup(BINDING_NAME);

            System.out.println(String.format("Успешное подключение к registry %s:%d...", host, port));

            while (true) {
                System.out.print("Введите два числа (Ctrl+D - выход): ");
                double a = sc.nextDouble();
                double b = sc.nextDouble();

                sc.nextLine();

                System.out.print("Введите операцию (add, sub, mul, div): ");
                String op = sc.nextLine();

                switch (op) {
                    case "add", "sub", "mul", "div":
                        OperationDTO dto = new OperationDTO(op, a, b);
                        Double result = executor.execute(dto);

                        System.out.println(String.format("Рузультат: %.2f", result));
                        break;
                    default:
                        System.out.println(String.format("оперция '%s' не поддреживается", op));
                        continue;
                }
            }
        
        } catch (RemoteException | NotBoundException e) {
            System.err.println(String.format("ошибка на строне сервера: %s", e.toString()));
            System.exit(1);
        } catch (NoSuchElementException e) {
            // Ctrl+D нажат - выходим из цикла
            System.out.println("\nВыход из программы...");
        }
    }
}
