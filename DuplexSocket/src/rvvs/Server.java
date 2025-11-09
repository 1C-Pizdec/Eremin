package rvvs;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен. Ожидание клиента...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключён: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Получено от клиента: " + inputLine);

                if (inputLine.equalsIgnoreCase("exit")) {
                    out.println("Соединение закрыто. До свидания!");
                    break;
                }

                String[] parts = inputLine.trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        double side = Double.parseDouble(parts[0]);
                        double height = Double.parseDouble(parts[1]);
                        double area = 0.5 * side * height;
                        out.println("Площадь треугольника: " + area);
                    } catch (NumberFormatException e) {
                        out.println("Ошибка: введите два числа (сторона и высота)");
                    }
                } else {
                    out.println("Ошибка: ожидается два параметра (сторона и высота)");
                }
            }

            System.out.println("Клиент отключился.");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

