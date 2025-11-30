package ru.pechenkindd.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    private String address;
    private int port;
    private AtomicBoolean running = new AtomicBoolean(true);

    Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    void connect() {
        try (
            Socket so = new Socket(this.address, this.port);
            PrintWriter out = new PrintWriter(so.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
        ) {
            // Поток для чтения сообщений от сервера
            Thread serverListener = new Thread(() -> {
                try {
                    String serverMessage;
                    while (running.get() && (serverMessage = in.readLine()) != null) {
                        System.out.println("\n[SERVER]: " + serverMessage);
                        System.out.print("> "); // Перерисовываем приглашение
                    }
                } catch (IOException e) {
                    if (running.get()) {
                        System.err.println("Соединение с сервером разорвано: " + e.getMessage());
                    }
                }
            });
            serverListener.start();

            // Главный поток для ввода команд пользователя
            Scanner scan = new Scanner(System.in);
            System.out.println("Подключено к серверу. Введите команды (NEW,id,side,price,qty или CANCEL,id):");
            System.out.print("> ");
            
            while (running.get() && scan.hasNextLine()) {
                String inputLine = scan.nextLine();
                
                if ("exit".equalsIgnoreCase(inputLine)) {
                    running.set(false);
                    break;
                }
                
                if (!inputLine.trim().isEmpty()) {
                    out.println(inputLine);
                }
                System.out.print("> ");
            }

            scan.close();
            running.set(false);
            so.close(); // Закрываем сокет для завершения потока serverListener
            
        } catch (IOException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }

    public static void main(String[] argv) {
        Client c = new Client("localhost", 8080);
        c.connect();
    }
}
