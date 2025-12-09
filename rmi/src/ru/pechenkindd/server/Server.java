package ru.pechenkindd.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;


public class Server {

    public static final String BINDING_NAME = "server.expression_executor";

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Используйте аргумент командной строки - номер порта");
            System.exit(1);
        }

        int port = 8080;
        try {
            port = Integer.parseInt(args[0].trim());
        } catch (NumberFormatException e) {
            System.err.println("Используйте аргумент командной строки - номер порта");
            System.exit(1);
        }

        final ExpressionExecutorServer server = new ExpressionExecutorServer(); // создаём экземпляр RMI-сервиса

        try {
            final Registry registry = LocateRegistry.createRegistry(port);
            
            Remote stub = UnicastRemoteObject.exportObject(server, 0); // можно было класс Server отнаследовать от UnicastRemoteObject

            registry.rebind(BINDING_NAME, stub);

            System.out.println(String.format("RMI-сервис слушает порт %d...", port));

        } catch (RemoteException e){
            System.err.println(String.format("Ops... что-то не так: %s", e.toString()));
            System.exit(1);
        }
    }
}
