package RMIcheck;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Пытаюсь подключиться к серверу...");
            
            
            Thread.sleep(2000);
            
            RemoteInterface calculator = (RemoteInterface) Naming.lookup("rmi://127.0.0.1:1099/CalculatorService");
            
            System.out.println("✓ Подключение к серверу установлено!");
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\n=== RMI Калькулятор ===");
                System.out.println("1. Сложить два числа");
                System.out.println("2. Информация о сервере");
                System.out.println("3. Выход");
                System.out.print("Выберите действие: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        System.out.print("Введите первое число: ");
                        int a = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Введите второе число: ");
                        int b = Integer.parseInt(scanner.nextLine());
                        
                        System.out.println("Отправляю запрос на сервер...");
                        int result = calculator.calculateSum(a, b);
                        System.out.println("✓ Результат: " + a + " + " + b + " = " + result);
                        break;
                        
                    case "2":
                        String info = calculator.getServerInfo();
                        System.out.println("Информация о сервере: " + info);
                        break;
                        
                    case "3":
                        System.out.println("Завершение работы...");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("Неверный выбор!");
                }
            }
            
        } catch (Exception e) {
            System.err.println("❌ Ошибка клиента: " + e.getMessage());
            System.out.println("Убедитесь, что сервер запущен!");
            e.printStackTrace();
        }
    }
}