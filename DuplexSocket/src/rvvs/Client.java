package rvvs;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Подключено к серверу.");
            System.out.println("Введите сторону и высоту через пробел (или 'exit' для выхода):");

            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                out.println(message);

                String response = in.readLine();
                if (response == null) break;

                System.out.println("Сервер: " + response);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            System.out.println("Клиент завершил работу.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}