package RMIcheck;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            
            System.out.println("Запуск RMI сервера...");
            
        
            try {
                LocateRegistry.getRegistry(1099).list();
                System.out.println("✓ Подключен к существующему RMI registry");
            } catch (Exception e) {
                LocateRegistry.createRegistry(1099);
                System.out.println("✓ Создан новый RMI registry на порту 1099");
            }
            
           
            RemoteImplementation remoteObj = new RemoteImplementation();
            
     
            Naming.rebind("rmi://127.0.0.1:1099/CalculatorService", remoteObj);
            System.out.println("✓ Сервер зарегистрирован как 'CalculatorService'");
            System.out.println("✓ Сервер готов принимать запросы...");
            
            // Бесконечный цикл чтобы сервер не закрывался
            while (true) {
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            System.err.println("❌ Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }
}